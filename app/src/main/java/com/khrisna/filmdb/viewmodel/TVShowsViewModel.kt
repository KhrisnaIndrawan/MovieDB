package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse

class TVShowsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _airingToday: MutableLiveData<TVShowsResponse>? = null
    private var _onTheAir: MutableLiveData<TVShowsResponse>? = null
    private var _popular: MutableLiveData<TVShowsResponse>? = null
    private var _topRated: MutableLiveData<TVShowsResponse>? = null

    val airingToday: LiveData<TVShowsResponse>?
        get() = _airingToday
    val onTheAir: LiveData<TVShowsResponse>?
        get() = _onTheAir
    val popular: LiveData<TVShowsResponse>?
        get() = _popular
    val topRated: LiveData<TVShowsResponse>?
        get() = _topRated

    fun getAiringToday() {
        _airingToday = movieRepository.getTVShowsAiringToday()
    }

    fun getOnTheAir() {
        _onTheAir = movieRepository.getTVShowsOnTheAir()
    }

    fun getPopular() {
        _popular = movieRepository.getTVShowsPopular()
    }

    fun getTopRate() {
        _topRated = movieRepository.getTVShowsTopRated()
    }
}