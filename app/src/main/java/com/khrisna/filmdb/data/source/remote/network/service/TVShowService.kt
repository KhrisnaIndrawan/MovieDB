package com.khrisna.filmdb.data.source.remote.network.service

import com.khrisna.filmdb.BuildConfig
import com.khrisna.filmdb.data.source.remote.response.TVShowResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowService {

    @GET("tv/{Id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getTVShow(@Path("Id") id: String): Call<TVShowResponse>

    @GET("tv/airing_today?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getTVAiringToday(@Query("page") page: String): Call<TVShowsResponse>

    @GET("tv/on_the_air?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getTVOnTheAir(@Query("page") page: String): Call<TVShowsResponse>

    @GET("tv/popular?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getTVPopular(@Query("page") page: String): Call<TVShowsResponse>

    @GET("tv/top_rated?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getTVTopRated(@Query("page") page: String): Call<TVShowsResponse>
}