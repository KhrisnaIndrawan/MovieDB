package com.khrisna.core.data.source.remote

import android.util.Log
import com.khrisna.core.data.source.remote.network.RetrofitServices
import com.khrisna.core.data.source.remote.response.*
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val retrofitServices: RetrofitServices) {

    companion object {
        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(service: RetrofitServices): RemoteDataSource =
            INSTANCE ?: synchronized(RemoteDataSource::class) {
                INSTANCE ?: RemoteDataSource(service)
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    fun search(query: String): Flow<SearchesResponse> =
        flow {
            try {
                val networkServices = retrofitServices.createSearchService()
                val response = networkServices.search(query)

                emit(response)
            } catch (e: Exception) {
                Log.e("RemoteRepository", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMovie(id: Int): Flow<ApiResponse<MovieResponse>> =
        flow {
            try {
                val networkServices = retrofitServices.createMovieService()
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

                val networkServices = retrofitServices.createMovieService()
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
                val networkServices = retrofitServices.createTVShowService()
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

                val networkServices = retrofitServices.createTVShowService()
                val response = networkServices.getTVShows(header.type, page)

                response.header = header.text

                emit(ApiResponse.Success(response))

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteRepository", e.toString())
            }
        }.flowOn(Dispatchers.IO)
}