package com.khrisna.core.data.source.remote.network.service

import com.khrisna.core.BuildConfig
import com.khrisna.core.data.source.remote.response.TVShowResponse
import com.khrisna.core.data.source.remote.response.TVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowService {

    @GET("tv/{id}?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getTVShow(@Path("id") id: Int): Call<TVShowResponse>

    @GET("tv/{header}?api_key=${BuildConfig.API_KEY}&language=en-US?")
    fun getTVShows(
        @Path("header") header: String,
        @Query("page") page: String
    ): Call<TVShowsResponse>
}