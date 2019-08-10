package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.khrisna.filmdb.data.source.remote.ApiResponse
import com.khrisna.filmdb.data.source.remote.StatusResponse
import com.khrisna.filmdb.data.source.vo.Resource
import com.khrisna.filmdb.utils.AppExecutors


abstract class NetworkBoundResource<ResultType, RequestType>() {

    constructor(appExecutors: AppExecutors) : this() {

        this.appExecutors = appExecutors

        result.value = Resource()

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
                StatusResponse.SUCCESS -> appExecutors?.diskIO()?.execute {

                    saveCallResult(response.body!!)

                    appExecutors!!.mainThread().execute {
                        result.addSource(
                            loadFromDB()
                        ) { newData -> result.setValue(Resource<ResultType>().success(newData)) }
                    }
                }
                StatusResponse.EMPTY -> appExecutors?.mainThread()?.execute {
                    result.addSource(
                        loadFromDB()
                    ) { newData -> result.setValue(Resource<ResultType>().success(newData)) }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.setValue(
                            Resource<ResultType>()
                                .error(response.message!!, newData)
                        )
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    private val result = MediatorLiveData<Resource<ResultType>>()

    private var appExecutors: AppExecutors? = null

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)
}