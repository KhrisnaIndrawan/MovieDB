package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khrisna.core.domain.model.Movies
import com.khrisna.core.domain.model.TVShows
import com.khrisna.core.domain.usecase.MovieUseCase
import com.khrisna.core.domain.usecase.TVShowUseCase

class ViewAllViewModel(
    private val moviesUseCase: MovieUseCase,
    private val tvShowUseCase: TVShowUseCase
) : ViewModel() {

    fun getMovies(header: String): LiveData<Movies> {
        return moviesUseCase.getMoviesByHeader(header).asLiveData()
    }

    fun getTVShows(header: String): LiveData<TVShows> {
        return tvShowUseCase.getTVShowsByHeader(header).asLiveData()
    }
}