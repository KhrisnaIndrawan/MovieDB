package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.remote.response.MovieResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowResponse

class DetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movieResponse: MutableLiveData<MovieResponse>? = null
    private var _tvShowResponse: MutableLiveData<TVShowResponse>? = null

    val movieResponse: LiveData<MovieResponse>?
        get() = _movieResponse
    val tvShowResponse: LiveData<TVShowResponse>?
        get() = _tvShowResponse

    fun getMovie(id: String) {
        _movieResponse = movieRepository.getMovie(id)
    }

    fun getTVShow(id: String) {
        _tvShowResponse = movieRepository.getTVShow(id)
    }
}