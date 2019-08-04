package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.data.source.vo.Resource

interface MovieDataSource {

    fun getMovie(id: String): LiveData<Resource<MovieEntity>>

    fun getMoviesNowPlaying(page: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesUpComing(page: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesPopular(page: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesTopRated(page: String): LiveData<Resource<MoviesEntity>>

    fun getTVShow(id: String): LiveData<Resource<TVShowEntity>>

    fun getTVShowsAiringToday(page: String): LiveData<Resource<TVShowsEntity>>

    fun getTVShowsOnTheAir(page: String): LiveData<Resource<TVShowsEntity>>

    fun getTVShowsPopular(page: String): LiveData<Resource<TVShowsEntity>>

    fun getTVShowsTopRated(page: String): LiveData<Resource<TVShowsEntity>>
}