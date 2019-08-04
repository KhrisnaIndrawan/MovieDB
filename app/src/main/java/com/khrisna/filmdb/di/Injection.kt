package com.khrisna.filmdb.di

import android.app.Application
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.LocalRepository
import com.khrisna.filmdb.data.source.local.room.MovieDao
import com.khrisna.filmdb.data.source.local.room.MovieDatabase
import com.khrisna.filmdb.data.source.remote.RemoteRepository
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.utils.AppExecutors

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