package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.di.Injection


class ViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(movieRepository: MovieRepository): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class) {
                    INSTANCE = ViewModelFactory(movieRepository)
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(movieRepository) as T
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(movieRepository) as T
            modelClass.isAssignableFrom(TVShowsViewModel::class.java) -> TVShowsViewModel(movieRepository) as T
            modelClass.isAssignableFrom(ViewAllViewModel::class.java) -> ViewAllViewModel(movieRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}