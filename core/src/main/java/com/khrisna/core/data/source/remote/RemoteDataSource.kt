package com.khrisna.core.data.source.remote

import android.util.Log
import com.khrisna.core.data.source.remote.network.service.ApiService
import com.khrisna.core.data.source.remote.response.*
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(val networkServices: ApiService) {

    fun search(query: String): Flow<SearchesResponse> =
        flow {
            try {
                val response = networkServices.search(query)

                emit(response)
            } catch (e: Exception) {
                Log.e("RemoteRepository", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMovie(id: Int): Flow<ApiResponse<MovieResponse>> =
        flow {
            try {
                val response = networkServices.getMovie(id)

                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteRepository", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMovies(header: ReleaseType, page: String): Flow<ApiResponse<MoviesResponse>> =
        flow {
            try {
                val response = networkServices.getMovies(header.type, page)

                response.header = header.text

                emit(ApiResponse.Success(response))

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteRepository", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    fun getTVShow(id: Int): Flow<ApiResponse<TVShowResponse>> =
        flow {
            try {
                val response = networkServices.getTVShow(id)

                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteRepository", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    fun getTVShows(header: ReleaseType, page: String): Flow<ApiResponse<TVShowsResponse>> =
        flow {
            try {
                val response = networkServices.getTVShows(header.type, page)

                response.header = header.text

                emit(ApiResponse.Success(response))

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteRepository", e.toString())
            }
        }.flowOn(Dispatchers.IO)
}