package com.khrisna.filmdb.network

import com.khrisna.filmdb.BuildConfig.API_KEY
import com.khrisna.filmdb.data.model.Movie
import com.khrisna.filmdb.data.model.Movies
import com.khrisna.filmdb.data.model.TVShow
import com.khrisna.filmdb.data.model.TVShows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServices {

    @GET("movie/{Id}?api_key=$API_KEY&language=en-US")
    fun getMovie(@Path("Id") id: String): Call<Movie>

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US&page=1")
    fun getMovieNowPlaying(): Call<Movies>

    @GET("movie/upcoming?api_key=$API_KEY&language=en-US&page=1")
    fun getMovieUpComing(): Call<Movies>

    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    fun getMoviePopular(): Call<Movies>

    @GET("movie/top_rated?api_key=$API_KEY&language=en-US&page=1")
    fun getMovieTopRated(): Call<Movies>

    @GET("tv/{Id}?api_key=$API_KEY&language=en-US")
    fun getTVShow(@Path("Id") id: String): Call<TVShow>

    @GET("tv/airing_today?api_key=$API_KEY&language=en-US&page=1")
    fun getTVAiringToday(): Call<TVShows>

    @GET("tv/on_the_air?api_key=$API_KEY&language=en-US&page=1")
    fun getTVOnTheAir(): Call<TVShows>

    @GET("tv/popular?api_key=$API_KEY&language=en-US&page=1")
    fun getTVPopular(): Call<TVShows>

    @GET("tv/top_rated?api_key=$API_KEY&language=en-US&page=1")
    fun getTVTopRated(): Call<TVShows>
}