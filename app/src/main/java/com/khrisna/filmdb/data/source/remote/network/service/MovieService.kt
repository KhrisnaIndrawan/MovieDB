package com.khrisna.filmdb.data.source.remote.network.service

import com.khrisna.filmdb.BuildConfig
import com.khrisna.filmdb.data.source.remote.response.MovieResponse
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{Id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getMovie(@Path("Id") id: Int): Call<MovieResponse>

    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getMovieNowPlaying(@Query("page") page: String): Call<MoviesResponse>

    @GET("movie/upcoming?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getMovieUpComing(@Query("page") page: String): Call<MoviesResponse>

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getMoviePopular(@Query("page") page: String): Call<MoviesResponse>

    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getMovieTopRated(@Query("page") page: String): Call<MoviesResponse>
}