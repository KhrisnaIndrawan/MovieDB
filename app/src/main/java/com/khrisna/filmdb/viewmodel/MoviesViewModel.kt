package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.data.repository.MovieRepository

class MoviesViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
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
        _nowPlaying = movieRepository.getNowPlaying()
    }

    fun getUpComing() {
        _upComing = movieRepository.getUpComing()
    }

    fun getPopular() {
        _popular = movieRepository.getPopular()
    }

    fun getTopRate() {
        _topRated = movieRepository.getTopRated()
    }
}