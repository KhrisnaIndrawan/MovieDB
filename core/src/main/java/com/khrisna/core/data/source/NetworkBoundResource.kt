package com.khrisna.core.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.khrisna.core.data.source.remote.ApiResponse
import com.khrisna.core.data.source.remote.StatusResponse
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.utils.AppExecutors


abstract class NetworkBoundResource<ResultType, RequestType>(
    private val appExecutors: AppExecutors
) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource()

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.setValue(
                        Resource<ResultType>()
                            .success(newData)
                    )
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(
            dbSource
        ) { newData -> result.setValue(Resource<ResultType>().loading(newData)) }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS -> appExecutors.diskIO().execute {

                    response.body?.let { saveCallResult(it) }

                    appExecutors.mainThread().execute {
                        result.addSource(
                            loadFromDB()
                        ) { newData -> result.setValue(Resource<ResultType>().success(newData)) }
                    }
                }
                StatusResponse.EMPTY -> appExecutors.mainThread().execute {
                    result.addSource(
                        loadFromDB()
                    ) { newData -> result.setValue(Resource<ResultType>().success(newData)) }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.setValue(
                            response.message?.let {
                                Resource<ResultType>()
                                    .error(it, newData)
                            }
                        )
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    private fun onFetchFailed() {
        Log.d("onFetchFailed", "Error")
    }

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)
}