package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.core.data.source.MovieRepository
import com.khrisna.core.data.source.local.entity.MoviesEntity
import com.khrisna.core.data.source.local.entity.TVShowsEntity

class ViewAllViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies(header: String): LiveData<MoviesEntity> {
        return movieRepository.getMoviesByHeader(header)
    }

    fun getTVShows(header: String): LiveData<TVShowsEntity> {
        return movieRepository.getTVShowsByHeader(header)
    }
}