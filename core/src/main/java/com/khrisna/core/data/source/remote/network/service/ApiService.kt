package com.khrisna.core.data.source.remote.network.service

import com.khrisna.core.BuildConfig
import com.khrisna.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getMovie(@Path("id") id: Int): MovieResponse

    @GET("movie/{header}?api_key=${BuildConfig.API_KEY}&language=en-US?")
    suspend fun getMovies(
        @Path("header") header: String,
        @Query("page") page: String
    ): MoviesResponse

    @GET("search/multi?api_key=${BuildConfig.API_KEY}&language=en-US?&page=1&include_adult=false")
    suspend fun search(@Query("query") query: String): SearchesResponse

    @GET("tv/{id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    suspend fun getTVShow(@Path("id") id: Int): TVShowResponse

    @GET("tv/{header}?api_key=${BuildConfig.API_KEY}&language=en-US?")
    suspend fun getTVShows(
        @Path("header") header: String,
        @Query("page") page: String
    ): TVShowsResponse
}