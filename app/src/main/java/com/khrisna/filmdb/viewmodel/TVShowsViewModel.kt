package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.data.source.vo.Resource

class TVShowsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _airingToday: LiveData<Resource<TVShowsEntity>>? = null
    private var _onTheAir: LiveData<Resource<TVShowsEntity>>? = null
    private var _popular: LiveData<Resource<TVShowsEntity>>? = null
    private var _topRated: LiveData<Resource<TVShowsEntity>>? = null

    val airingToday: LiveData<Resource<TVShowsEntity>>?
        get() = _airingToday
    val onTheAir: LiveData<Resource<TVShowsEntity>>?
        get() = _onTheAir
    val popular: LiveData<Resource<TVShowsEntity>>?
        get() = _popular
    val topRated: LiveData<Resource<TVShowsEntity>>?
        get() = _topRated

    fun getAiringToday() {
        _airingToday = movieRepository.getTVShowsAiringToday("1")
    }

    fun getOnTheAir() {
        _onTheAir = movieRepository.getTVShowsOnTheAir("1")
    }

    fun getPopular() {
        _popular = movieRepository.getTVShowsPopular("1")
    }

    fun getTopRated() {
        _topRated = movieRepository.getTVShowsTopRated("1")
    }
}