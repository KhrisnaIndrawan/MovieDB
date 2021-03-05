package com.khrisna.core.data.source.local

import androidx.paging.DataSource
import com.khrisna.core.data.source.local.entity.*
import com.khrisna.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getFavoriteById(id: Int): Flow<FavoriteEntity> {
        return movieDao.getFavoriteById(id)
    }

    fun getFavoritesAsPaged(isMovie: Boolean): DataSource.Factory<Int, FavoriteEntity> {
        return movieDao.getFavoritesAsPaged(isMovie)
    }

    fun insertFavorite(favorite: FavoriteEntity) {
        movieDao.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: FavoriteEntity) {
        movieDao.deleteFavorite(favorite)
    }

    fun updateFavorite(favorite: FavoriteEntity) {
        movieDao.updateFavorite(favorite)
    }

    fun getMovieById(id: Int): Flow<MovieEntity> {
        return movieDao.getMovieById(id)
    }

    suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    fun updateMovie(movie: MovieEntity) {
        movieDao.updateMovie(movie)
    }

    fun getMoviesById(id: Int): Flow<MoviesEntity> {
        return movieDao.getMoviesById(id)
    }

    fun getMoviesByHeader(header: String): Flow<MoviesEntity> {
        return movieDao.getMoviesByHeader(header)
    }

    suspend fun insertMovies(moviesList: MoviesEntity) {
        movieDao.insertMovies(moviesList)
    }

    fun updateMovies(movies: MoviesEntity) {
        movieDao.updateMovies(movies)
    }

    fun getTVShowById(id: Int): Flow<TVShowEntity> {
        return movieDao.getTVShowById(id)
    }

    fun insertTVShow(tvShows: TVShowEntity) {
        movieDao.insertTVShow(tvShows)
    }

    fun updateTVShow(tvShow: TVShowEntity) {
        movieDao.updateTVShow(tvShow)
    }

    fun getTVShowsById(id: Int): Flow<TVShowsEntity> {
        return movieDao.getTVShowsById(id)
    }

    fun getTVShowsByHeader(header: String): Flow<TVShowsEntity> {
        return movieDao.getTVShowsByHeader(header)
    }

    suspend fun insertTVShows(tvShowsList: TVShowsEntity) {
        movieDao.insertTVShows(tvShowsList)
    }

    fun updateTVShows(tvShows: TVShowsEntity) {
        movieDao.updateTVShows(tvShows)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: MovieDao): LocalDataSource? {
            if (INSTANCE == null) {
                synchronized(LocalDataSource::class) {
                    INSTANCE = LocalDataSource(dao)
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}