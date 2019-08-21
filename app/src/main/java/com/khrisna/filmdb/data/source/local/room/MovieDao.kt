package com.khrisna.filmdb.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.khrisna.filmdb.data.source.local.entity.*


@Dao
interface MovieDao {

    // Favorite
    @Transaction
    @Query("SELECT * FROM favorite_entities WHERE favorite_id = :id")
    fun getFavoriteById(id: Int): LiveData<FavoriteEntity>

    @Query("SELECT * FROM favorite_entities WHERE favorite_is_movie = :isMovie")
    fun getFavoritesAsPaged(isMovie: Boolean): DataSource.Factory<Int, FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavorite(favorite: FavoriteEntity)

    @Delete
    fun deleteFavorite(favorite: FavoriteEntity)

    // Movie
    @Transaction
    @Query("SELECT * FROM movie_entities WHERE movie_id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: MovieEntity)

    // Movies
    @Transaction
    @Query("SELECT * FROM movies_entities WHERE movies_id = :id")
    fun getMoviesById(id: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM movies_entities WHERE movies_header = :header")
    fun getMoviesByHeader(header: String): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(moviesList: MoviesEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovies(movies: MoviesEntity)

    // TVShow
    @Transaction
    @Query("SELECT * FROM tv_show_entities WHERE tv_show_id = :id")
    fun getTVShowById(id: Int): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(tvShows: TVShowEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTVShow(tvShow: TVShowEntity)

    // TVShows
    @Transaction
    @Query("SELECT * FROM tv_shows_entities WHERE tv_shows_id = :id")
    fun getTVShowsById(id: Int): LiveData<TVShowsEntity>

    @Query("SELECT * FROM tv_shows_entities WHERE tv_shows_header = :header")
    fun getTVShowsByHeader(header: String): LiveData<TVShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShows(tvShowsList: TVShowsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTVShows(tvShows: TVShowsEntity)
}