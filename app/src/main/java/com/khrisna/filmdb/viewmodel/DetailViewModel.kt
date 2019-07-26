package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity

class DetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movieResponse: LiveData<MovieEntity>? = null
    private var _tvShowResponse: LiveData<TVShowEntity>? = null

    val movieResponse: LiveData<MovieEntity>?
        get() = _movieResponse
    val tvShowResponse: LiveData<TVShowEntity>?
        get() = _tvShowResponse

    fun getMovie(id: String) {
        _movieResponse = movieRepository.getMovie(id)
    }

    fun getTVShow(id: String) {
        _tvShowResponse = movieRepository.getTVShow(id)
    }
}