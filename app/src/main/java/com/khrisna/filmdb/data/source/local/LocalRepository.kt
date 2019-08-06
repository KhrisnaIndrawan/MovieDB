package com.khrisna.filmdb.data.source.local

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.data.source.local.room.MovieDao

class LocalRepository(private val movieDao: MovieDao) {

    fun getMovieById(id: Int): LiveData<MovieEntity> {
        return movieDao.getMovieById(id)
    }

    fun insertMovie(movies: MovieEntity) {
        return movieDao.insertMovie(movies)
    }

    fun updateMovie(movie: MovieEntity): Int {
        return movieDao.updateMovie(movie)
    }

    fun getMoviesById(id: Int): LiveData<MoviesEntity> {
        return movieDao.getMoviesById(id)
    }

    fun getMoviesByHeader(header: String): LiveData<MoviesEntity> {
        return movieDao.getMoviesByHeader(header)
    }

    fun insertMovies(moviesList: MoviesEntity) {
        return movieDao.insertMovies(moviesList)
    }

    fun updateMovies(movies: MoviesEntity): Int {
        return movieDao.updateMovies(movies)
    }

    fun getTVShowById(id: Int): LiveData<TVShowEntity> {
        return movieDao.getTVShowById(id)
    }

    fun insertTVShow(tvShows: TVShowEntity) {
        return movieDao.insertTVShow(tvShows)
    }

    fun updateTVShow(tvShow: TVShowEntity): Int {
        return movieDao.updateTVShow(tvShow)
    }

    fun getTVShowsById(id: Int): LiveData<TVShowsEntity> {
        return movieDao.getTVShowsById(id)
    }

    fun getTVShowsByHeader(header: String): LiveData<TVShowsEntity> {
        return movieDao.getTVShowsByHeader(header)
    }

    fun insertTVShows(tvShowsList: TVShowsEntity) {
        return movieDao.insertTVShows(tvShowsList)
    }

    fun updateTVShows(movies: TVShowsEntity): Int {
        return movieDao.updateTVShows(movies)
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