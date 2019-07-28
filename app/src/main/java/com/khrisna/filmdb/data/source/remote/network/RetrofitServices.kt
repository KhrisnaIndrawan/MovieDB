package com.khrisna.filmdb.data.source.remote.network

import com.khrisna.filmdb.BuildConfig.BASE_URL
import com.khrisna.filmdb.data.source.remote.network.service.MovieService
import com.khrisna.filmdb.data.source.remote.network.service.TVShowService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {

    fun createMovieService(): MovieService {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(MovieService::class.java)
    }

    fun createTVShowService(): TVShowService {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(TVShowService::class.java)
    }
}