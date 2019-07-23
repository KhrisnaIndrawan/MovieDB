package com.khrisna.filmdb.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.model.TVShows
import com.khrisna.filmdb.data.repository.TVShowRepository

class TVShowsViewModel : ViewModel() {

    private val tvShowRepository = TVShowRepository()
    private var _airingToday: MutableLiveData<TVShows>? = null
    private var _onTheAir: MutableLiveData<TVShows>? = null
    private var _popular: MutableLiveData<TVShows>? = null
    private var _topRated: MutableLiveData<TVShows>? = null

    val airingToday: LiveData<TVShows>?
        get() = _airingToday
    val onTheAir: LiveData<TVShows>?
        get() = _onTheAir
    val popular: LiveData<TVShows>?
        get() = _popular
    val topRated: LiveData<TVShows>?
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