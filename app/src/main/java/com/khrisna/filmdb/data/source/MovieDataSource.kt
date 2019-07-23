package com.khrisna.filmdb.data.source

import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.remote.response.MovieResponse
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse

interface MovieDataSource {

    fun getMovie(id: String): MutableLiveData<MovieResponse>

    fun getMoviesNowPlaying(): MutableLiveData<MoviesResponse>

    fun getMoviesUpComing(): MutableLiveData<MoviesResponse>

    fun getMoviesPopular(): MutableLiveData<MoviesResponse>

    fun getMoviesTopRated(): MutableLiveData<MoviesResponse>

    fun getTVShow(id: String): MutableLiveData<TVShowResponse>

    fun getTVShowsAiringToday(): MutableLiveData<TVShowsResponse>

    fun getTVShowsOnTheAir(): MutableLiveData<TVShowsResponse>

    fun getTVShowsPopular(): MutableLiveData<TVShowsResponse>

    fun getTVShowsTopRated(): MutableLiveData<TVShowsResponse>
}