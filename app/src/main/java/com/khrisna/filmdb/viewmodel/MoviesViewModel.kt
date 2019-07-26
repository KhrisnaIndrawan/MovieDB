package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity

class MoviesViewModel(
    movieRepository: MovieRepository
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

    init {
        _nowPlaying = movieRepository.getMoviesNowPlaying()
        _upComing = movieRepository.getMoviesUpComing()
        _popular = movieRepository.getMoviesPopular()
        _topRated = movieRepository.getMoviesTopRated()
    }
}