package com.khrisna.filmdb.data.source.remote.network

import com.khrisna.filmdb.BuildConfig.API_KEY
import com.khrisna.filmdb.data.source.remote.response.MovieResponse
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataServices {

    @GET("movie/{Id}?api_key=$API_KEY&language=en-US")
    fun getMovie(@Path("Id") id: String): Call<MovieResponse>

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US&page=1")
    fun getMovieNowPlaying(): Call<MoviesResponse>

    @GET("movie/upcoming?api_key=$API_KEY&language=en-US&page=1")
    fun getMovieUpComing(): Call<MoviesResponse>

    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    fun getMoviePopular(): Call<MoviesResponse>

    @GET("movie/top_rated?api_key=$API_KEY&language=en-US&page=1")
    fun getMovieTopRated(): Call<MoviesResponse>

    @GET("tv/{Id}?api_key=$API_KEY&language=en-US")
    fun getTVShow(@Path("Id") id: String): Call<TVShowResponse>

    @GET("tv/airing_today?api_key=$API_KEY&language=en-US&page=1")
    fun getTVAiringToday(): Call<TVShowsResponse>

    @GET("tv/on_the_air?api_key=$API_KEY&language=en-US&page=1")
    fun getTVOnTheAir(): Call<TVShowsResponse>

    @GET("tv/popular?api_key=$API_KEY&language=en-US&page=1")
    fun getTVPopular(): Call<TVShowsResponse>

    @GET("tv/top_rated?api_key=$API_KEY&language=en-US&page=1")
    fun getTVTopRated(): Call<TVShowsResponse>
}