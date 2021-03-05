package com.khrisna.core.di

import android.content.Context
import com.khrisna.core.data.source.FavoriteRepository
import com.khrisna.core.data.source.MovieRepository
import com.khrisna.core.data.source.SearchRepository
import com.khrisna.core.data.source.TVShowRepository
import com.khrisna.core.data.source.local.LocalDataSource
import com.khrisna.core.data.source.local.room.MovieDao
import com.khrisna.core.data.source.local.room.MovieDatabase
import com.khrisna.core.data.source.remote.RemoteDataSource
import com.khrisna.core.data.source.remote.network.RetrofitServices
import com.khrisna.core.domain.repository.IFavoriteRepository
import com.khrisna.core.domain.repository.IMovieRepository
import com.khrisna.core.domain.repository.ISearchRepository
import com.khrisna.core.domain.repository.ITVShowRepository
import com.khrisna.core.domain.usecase.*

object Injection {

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideMovieRepository(context)
        return MovieInteractor(repository)
    }

    fun provideTVShowUseCase(context: Context): TVShowUseCase {
        val repository = provideTVShowRepository(context)
        return TVShowInteractor(repository)
    }

    fun provideFavoriteUseCase(context: Context): FavoriteUseCase {
        val repository = provideFavoriteRepository(context)
        return FavoriteInteractor(repository)
    }

    fun provideSearchUseCase(context: Context): SearchUseCase {
        val repository = provideSearchRepository(context)
        return SearchInteractor(repository)
    }

    private fun provideMovieRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(RetrofitServices)
        val localDataSource = LocalDataSource.getInstance(database?.movieDao() as MovieDao)

        return MovieRepository.getInstance(localDataSource as LocalDataSource, remoteDataSource)
    }

    private fun provideTVShowRepository(context: Context): ITVShowRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(RetrofitServices)
        val localDataSource = LocalDataSource.getInstance(database?.movieDao() as MovieDao)

        return TVShowRepository.getInstance(localDataSource as LocalDataSource, remoteDataSource)
    }

    private fun provideFavoriteRepository(context: Context): IFavoriteRepository {
        val database = MovieDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database?.movieDao() as MovieDao)

        return FavoriteRepository.getInstance(localDataSource as LocalDataSource)
    }

    private fun provideSearchRepository(context: Context): ISearchRepository {
        val remoteDataSource = RemoteDataSource.getInstance(RetrofitServices)

        return SearchRepository.getInstance(remoteDataSource)
    }
}