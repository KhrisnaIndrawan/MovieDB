package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse
import com.khrisna.filmdb.data.repository.TVShowRepository

class TVShowsViewModel : ViewModel() {

    private val tvShowRepository = TVShowRepository()
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
        _airingToday = tvShowRepository.getAiringToday()
    }

    fun getOnTheAir() {
        _onTheAir = tvShowRepository.getOnTheAir()
    }

    fun getPopular() {
        _popular = tvShowRepository.getPopular()
    }

    fun getTopRate() {
        _topRated = tvShowRepository.getTopRated()
    }
}