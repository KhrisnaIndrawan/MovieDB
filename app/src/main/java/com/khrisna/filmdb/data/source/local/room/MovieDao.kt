package com.khrisna.filmdb.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity

@Dao
interface MovieDao {

    // Movie
    @Transaction
    @Query("SELECT * FROM movie_entities WHERE movie_id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: MovieEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMovie(movie: MovieEntity): Int

    // Movies
    @Transaction
    @Query("SELECT * FROM movies_entities WHERE movies_id = :id")
    fun getMoviesById(id: Int): LiveData<MoviesEntity>

    @Transaction
    @Query("SELECT * FROM movies_entities WHERE movies_id = :header")
    fun getMoviesByHeader(header: String): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(moviesList: MoviesEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMovies(movies: MoviesEntity): Int

    // TVShow
    @Transaction
    @Query("SELECT * FROM tv_show_entities WHERE tv_show_id = :id")
    fun getTVShowById(id: Int): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(tvShows: TVShowEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateTVShow(tvShow: TVShowEntity): Int

    // TVShows
    @Transaction
    @Query("SELECT * FROM tv_shows_entities WHERE tv_shows_id = :id")
    fun getTVShowsById(id: Int): LiveData<TVShowsEntity>

    @Transaction
    @Query("SELECT * FROM tv_shows_entities WHERE tv_shows_header = :header")
    fun getTVShowsByHeader(header: String): LiveData<TVShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShows(tvShowsList: TVShowsEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateTVShows(movies: TVShowsEntity): Int
}