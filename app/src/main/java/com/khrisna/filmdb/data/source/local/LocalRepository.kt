package com.khrisna.filmdb.data.source.local

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.*
import com.khrisna.filmdb.data.source.local.room.MovieDao

class LocalRepository(private val movieDao: MovieDao) {

    fun getFavoriteById(id: Int): LiveData<FavoriteEntity> {
        return movieDao.getFavoriteById(id)
    }

    fun getFavorites(): LiveData<List<FavoriteEntity>> {
        return movieDao.getFavorites()
    }

    fun insertFavorite(favorite: FavoriteEntity) {
        movieDao.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: FavoriteEntity) {
        movieDao.deleteFavorite(favorite)
    }

    fun getMovieById(id: Int): LiveData<MovieEntity> {
        return movieDao.getMovieById(id)
    }

    fun insertMovie(movies: MovieEntity) {
        movieDao.insertMovie(movies)
    }

    fun getMoviesById(id: Int): LiveData<MoviesEntity> {
        return movieDao.getMoviesById(id)
    }

    fun insertMovies(moviesList: MoviesEntity) {
        movieDao.insertMovies(moviesList)
    }

    fun getTVShowById(id: Int): LiveData<TVShowEntity> {
        return movieDao.getTVShowById(id)
    }

    fun insertTVShow(tvShows: TVShowEntity) {
        movieDao.insertTVShow(tvShows)
    }

    fun getTVShowsById(id: Int): LiveData<TVShowsEntity> {
        return movieDao.getTVShowsById(id)
    }

    fun insertTVShows(tvShowsList: TVShowsEntity) {
        movieDao.insertTVShows(tvShowsList)
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