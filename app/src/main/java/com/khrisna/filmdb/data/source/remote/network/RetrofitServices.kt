package com.khrisna.filmdb.data.source.remote.network

import com.khrisna.filmdb.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {

    fun create(): DataServices {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(DataServices::class.java)
    }
}