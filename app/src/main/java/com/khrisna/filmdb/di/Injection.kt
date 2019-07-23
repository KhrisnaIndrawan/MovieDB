package com.khrisna.filmdb.di

import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.LocalRepository
import com.khrisna.filmdb.data.source.remote.RemoteRepository
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices

object Injection {

    fun provideRepository(): MovieRepository {

        val retrofitServices = RetrofitServices
        val localRepository = LocalRepository()
        val remoteRepository = RemoteRepository(retrofitServices)

        return MovieRepository(localRepository, remoteRepository)
    }
}