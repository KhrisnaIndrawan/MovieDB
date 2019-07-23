package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.model.Movies
import com.khrisna.filmdb.data.repository.MovieRepository

class MoviesViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private var _nowPlaying: MutableLiveData<Movies>? = null
    private var _upComing: MutableLiveData<Movies>? = null
    private var _popular: MutableLiveData<Movies>? = null
    private var _topRated: MutableLiveData<Movies>? = null

    val nowPlaying: LiveData<Movies>?
        get() = _nowPlaying
    val upComing: LiveData<Movies>?
        get() = _upComing
    val popular: LiveData<Movies>?
        get() = _popular
    val topRated: LiveData<Movies>?
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