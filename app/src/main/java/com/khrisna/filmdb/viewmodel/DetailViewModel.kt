package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.vo.Resource

class DetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movie: LiveData<Resource<MovieEntity>>? = null
    private var _tvShow: LiveData<Resource<TVShowEntity>>? = null

    val movie: LiveData<Resource<MovieEntity>>?
        get() = _movie
    val tvShow: LiveData<Resource<TVShowEntity>>?
        get() = _tvShow

    fun getMovie(id: String) {
        _movie = movieRepository.getMovie(id)
    }

    fun getTVShow(id: String) {
        _tvShow = movieRepository.getTVShow(id)
    }
}