package com.khrisna.filmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.model.Movie
import com.khrisna.filmdb.data.model.TVShow
import com.khrisna.filmdb.data.repository.MovieRepository
import com.khrisna.filmdb.data.repository.TVShowRepository

class DetailViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private val tvShowRepository = TVShowRepository()

    private var _movie: MutableLiveData<Movie>? = null
    private var _tvShow: MutableLiveData<TVShow>? = null

    val movie: LiveData<Movie>?
        get() = _movie
    val tvShow: LiveData<TVShow>?
        get() = _tvShow

    fun getMovie(id: String) {
        _movie = movieRepository.getMovie(id)
    }

    fun getTVShow(id: String) {
        _tvShow = tvShowRepository.getTVShow(id)
    }
}