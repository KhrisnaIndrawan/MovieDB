package com.khrisna.core.data.source.remote.network.service

import com.khrisna.core.BuildConfig
import com.khrisna.core.data.source.remote.response.MovieResponse
import com.khrisna.core.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getMovie(@Path("id") id: Int): Call<MovieResponse>

    @GET("movie/{header}?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getMovies(
        @Path("header") header: String,
        @Query("page") page: String
    ): Call<MoviesResponse>
}