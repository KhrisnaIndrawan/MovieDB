package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity

interface MovieDataSource {

    fun getMovie(id: String): LiveData<MovieEntity>

    fun getMoviesNowPlaying(): LiveData<MoviesEntity>

    fun getMoviesUpComing(): LiveData<MoviesEntity>

    fun getMoviesPopular(): LiveData<MoviesEntity>

    fun getMoviesTopRated(): LiveData<MoviesEntity>

    fun getTVShow(id: String): LiveData<TVShowEntity>

    fun getTVShowsAiringToday(): LiveData<TVShowsEntity>

    fun getTVShowsOnTheAir(): LiveData<TVShowsEntity>

    fun getTVShowsPopular(): LiveData<TVShowsEntity>

    fun getTVShowsTopRated(): LiveData<TVShowsEntity>
}