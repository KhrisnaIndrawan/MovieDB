package com.khrisna.core.data.source

import com.khrisna.core.data.source.remote.ApiResponse
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.data.source.vo.Resource.*
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}