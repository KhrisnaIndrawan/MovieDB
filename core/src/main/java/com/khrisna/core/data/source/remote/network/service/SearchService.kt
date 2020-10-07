package com.khrisna.core.data.source.remote.network.service

import com.khrisna.core.data.source.remote.response.SearchesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/multi?api_key={BuildConfig.API_KEY}&language=en-US?&page=1&include_adult=false")
    fun search(@Query("query") query: String): Call<SearchesResponse>
}