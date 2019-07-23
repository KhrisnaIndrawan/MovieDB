package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse

class MoviesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _nowPlaying: MutableLiveData<MoviesResponse>? = null
    private var _upComing: MutableLiveData<MoviesResponse>? = null
    private var _popular: MutableLiveData<MoviesResponse>? = null
    private var _topRated: MutableLiveData<MoviesResponse>? = null

    val nowPlaying: LiveData<MoviesResponse>?
        get() = _nowPlaying
    val upComing: LiveData<MoviesResponse>?
        get() = _upComing
    val popular: LiveData<MoviesResponse>?
        get() = _popular
    val topRated: LiveData<MoviesResponse>?
        get() = _topRated

    fun getNowPlaying() {
        _nowPlaying = movieRepository.getMoviesNowPlaying()
    }

    fun getUpComing() {
        _upComing = movieRepository.getMoviesUpComing()
    }

    fun getPopular() {
        _popular = movieRepository.getMoviesPopular()
    }

    fun getTopRate() {
        _topRated = movieRepository.getMoviesTopRated()
    }
}