package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity

class TVShowsViewModel(
    movieRepository: MovieRepository
) : ViewModel() {

    private var _airingToday: LiveData<TVShowsEntity>? = null
    private var _onTheAir: LiveData<TVShowsEntity>? = null
    private var _popular: LiveData<TVShowsEntity>? = null
    private var _topRated: LiveData<TVShowsEntity>? = null

    val airingToday: LiveData<TVShowsEntity>?
        get() = _airingToday
    val onTheAir: LiveData<TVShowsEntity>?
        get() = _onTheAir
    val popular: LiveData<TVShowsEntity>?
        get() = _popular
    val topRated: LiveData<TVShowsEntity>?
        get() = _topRated

    init {
        _airingToday = movieRepository.getTVShowsAiringToday()
        _onTheAir = movieRepository.getTVShowsOnTheAir()
        _popular = movieRepository.getTVShowsPopular()
        _topRated = movieRepository.getTVShowsTopRated()
    }
}