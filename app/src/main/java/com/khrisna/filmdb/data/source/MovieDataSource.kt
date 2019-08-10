package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.*
import com.khrisna.filmdb.data.source.vo.Resource

interface MovieDataSource {

    fun getFavorite(id: Int): LiveData<FavoriteEntity>?

    fun getFavorites(): LiveData<List<FavoriteEntity>>

    fun insertFavorite(favorite: FavoriteEntity)

    fun deleteFavorite(favorite: FavoriteEntity)

    fun updateFavorite(favorite: FavoriteEntity)

    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getMoviesNowPlaying(page: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesUpComing(page: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesPopular(page: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesTopRated(page: String): LiveData<Resource<MoviesEntity>>

    fun getTVShow(id: Int): LiveData<Resource<TVShowEntity>>

    fun getTVShowsAiringToday(page: String): LiveData<Resource<TVShowsEntity>>

    fun getTVShowsOnTheAir(page: String): LiveData<Resource<TVShowsEntity>>

    fun getTVShowsPopular(page: String): LiveData<Resource<TVShowsEntity>>

    fun getTVShowsTopRated(page: String): LiveData<Resource<TVShowsEntity>>
}