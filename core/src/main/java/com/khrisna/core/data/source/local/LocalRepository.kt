package com.khrisna.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.khrisna.core.data.source.local.entity.*
import com.khrisna.core.data.source.local.room.MovieDao

class LocalRepository(private val movieDao: MovieDao) {

    fun getFavoriteById(id: Int): LiveData<FavoriteEntity> {
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

    fun getMovieById(id: Int): LiveData<MovieEntity> {
        return movieDao.getMovieById(id)
    }

    fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    fun updateMovie(movie: MovieEntity) {
        movieDao.updateMovie(movie)
    }

    fun getMoviesById(id: Int): LiveData<MoviesEntity> {
        return movieDao.getMoviesById(id)
    }

    fun getMoviesByHeader(header: String): LiveData<MoviesEntity> {
        return movieDao.getMoviesByHeader(header)
    }

    fun insertMovies(moviesList: MoviesEntity) {
        movieDao.insertMovies(moviesList)
    }

    fun updateMovies(movies: MoviesEntity) {
        movieDao.updateMovies(movies)
    }

    fun getTVShowById(id: Int): LiveData<TVShowEntity> {
        return movieDao.getTVShowById(id)
    }

    fun insertTVShow(tvShows: TVShowEntity) {
        movieDao.insertTVShow(tvShows)
    }

    fun updateTVShow(tvShow: TVShowEntity) {
        movieDao.updateTVShow(tvShow)
    }

    fun getTVShowsById(id: Int): LiveData<TVShowsEntity> {
        return movieDao.getTVShowsById(id)
    }

    fun getTVShowsByHeader(header: String): LiveData<TVShowsEntity> {
        return movieDao.getTVShowsByHeader(header)
    }

    fun insertTVShows(tvShowsList: TVShowsEntity) {
        movieDao.insertTVShows(tvShowsList)
    }

    fun updateTVShows(tvShows: TVShowsEntity) {
        movieDao.updateTVShows(tvShows)
    }

    companion object {
        private var INSTANCE: LocalRepository? = null

        fun getInstance(dao: MovieDao): LocalRepository? {
            if (INSTANCE == null) {
                synchronized(LocalRepository::class) {
                    INSTANCE = LocalRepository(dao)
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}