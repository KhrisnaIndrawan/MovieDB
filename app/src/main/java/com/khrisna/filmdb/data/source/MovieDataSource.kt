package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse

interface MovieDataSource {

    fun getMovie(id: String): LiveData<MovieEntity>

    fun getMoviesNowPlaying(): LiveData<MoviesResponse>

    fun getMoviesUpComing(): LiveData<MoviesResponse>

    fun getMoviesPopular(): LiveData<MoviesResponse>

    fun getMoviesTopRated(): LiveData<MoviesResponse>

    fun getTVShow(id: String): LiveData<TVShowEntity>

    fun getTVShowsAiringToday(): LiveData<TVShowsResponse>

    fun getTVShowsOnTheAir(): LiveData<TVShowsResponse>

    fun getTVShowsPopular(): LiveData<TVShowsResponse>

    fun getTVShowsTopRated(): LiveData<TVShowsResponse>
}