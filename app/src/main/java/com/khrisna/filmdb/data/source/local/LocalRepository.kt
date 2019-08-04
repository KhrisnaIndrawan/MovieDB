package com.khrisna.filmdb.data.source.local

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.*
import com.khrisna.filmdb.data.source.local.room.MovieDao

class LocalRepository(private val movieDao: MovieDao) {

    fun getMovieById(id: String): LiveData<MovieEntity> {
        return movieDao.getMovieById(id)
    }

    fun insertMovie(movies: MovieEntity) {
        return movieDao.insertMovie(movies)
    }

    fun updateMovie(movie: MovieEntity): Int {
        return movieDao.updateMovie(movie)
    }

    fun getMoviesById(id: String): LiveData<MoviesEntity> {
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

    fun getTVShowById(id: String): LiveData<TVShowEntity> {
        return movieDao.getTVShowById(id)
    }

    fun insertTVShow(tvShows: TVShowEntity) {
        return movieDao.insertTVShow(tvShows)
    }

    fun updateTVShow(tvShow: TVShowEntity): Int {
        return movieDao.updateTVShow(tvShow)
    }

    fun getTVShowsById(id: String): LiveData<TVShowsEntity> {
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

    fun getGenreById(id: String): LiveData<GenreEntity> {
        return movieDao.getGenreById(id)
    }

    fun insertGenre(genres: GenreEntity) {
        return movieDao.insertGenre(genres)
    }

    fun updateGenre(genre: GenreEntity): Int {
        return movieDao.updateGenre(genre)
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