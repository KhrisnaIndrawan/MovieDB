package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity

class MoviesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _nowPlaying: LiveData<MoviesEntity>? = null
    private var _upComing: LiveData<MoviesEntity>? = null
    private var _popular: LiveData<MoviesEntity>? = null
    private var _topRated: LiveData<MoviesEntity>? = null

    val nowPlaying: LiveData<MoviesEntity>?
        get() = _nowPlaying
    val upComing: LiveData<MoviesEntity>?
        get() = _upComing
    val popular: LiveData<MoviesEntity>?
        get() = _popular
    val topRated: LiveData<MoviesEntity>?
        get() = _topRated

    fun getNowPlaying() {
        _nowPlaying = movieRepository.getMoviesNowPlaying("1")
    }

    fun getUpComing() {
        _upComing = movieRepository.getMoviesUpComing("1")
    }

    fun getPopular() {
        _popular = movieRepository.getMoviesPopular("1")
    }

    fun getTopRated() {
        _topRated = movieRepository.getMoviesTopRated("1")
    }
}