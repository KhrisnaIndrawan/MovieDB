package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity

interface MovieDataSource {

    fun getMovie(id: String): LiveData<MovieEntity>

    fun getMoviesNowPlaying(page: String): LiveData<MoviesEntity>

    fun getMoviesUpComing(page: String): LiveData<MoviesEntity>

    fun getMoviesPopular(page: String): LiveData<MoviesEntity>

    fun getMoviesTopRated(page: String): LiveData<MoviesEntity>

    fun getTVShow(id: String): LiveData<TVShowEntity>

    fun getTVShowsAiringToday(page: String): LiveData<TVShowsEntity>

    fun getTVShowsOnTheAir(page: String): LiveData<TVShowsEntity>

    fun getTVShowsPopular(page: String): LiveData<TVShowsEntity>

    fun getTVShowsTopRated(page: String): LiveData<TVShowsEntity>
}