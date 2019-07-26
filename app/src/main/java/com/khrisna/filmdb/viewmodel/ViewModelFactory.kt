package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khrisna.filmdb.data.source.MovieRepository


class ViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(movieRepository) as T
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(movieRepository) as T
            modelClass.isAssignableFrom(TVShowsViewModel::class.java) -> TVShowsViewModel(movieRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}