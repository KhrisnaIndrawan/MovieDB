package com.khrisna.core.di

import android.app.Application
import com.khrisna.core.data.source.MovieRepository
import com.khrisna.core.data.source.local.LocalRepository
import com.khrisna.core.data.source.local.room.MovieDao
import com.khrisna.core.data.source.local.room.MovieDatabase
import com.khrisna.core.data.source.remote.RemoteRepository
import com.khrisna.core.data.source.remote.network.RetrofitServices
import com.khrisna.core.utils.AppExecutors

object Injection {

    fun provideRepository(application: Application): MovieRepository {

        val movieDatabase = MovieDatabase.getInstance(application)
        val retrofitServices = RetrofitServices

        val localRepository = LocalRepository.getInstance(movieDatabase?.movieDao() as MovieDao) as LocalRepository
        val remoteRepository = RemoteRepository.getInstance(retrofitServices) as RemoteRepository

        val appExecutors = AppExecutors()

        return MovieRepository(localRepository, remoteRepository, appExecutors)
    }
}