package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity

class DetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movie: LiveData<MovieEntity>? = null
    private var _tvShow: LiveData<TVShowEntity>? = null

    val movie: LiveData<MovieEntity>?
        get() = _movie
    val tvShow: LiveData<TVShowEntity>?
        get() = _tvShow

    fun getMovie(id: String) {
        _movie = movieRepository.getMovie(id)
    }

    fun getTVShow(id: String) {
        _tvShow = movieRepository.getTVShow(id)
    }
}