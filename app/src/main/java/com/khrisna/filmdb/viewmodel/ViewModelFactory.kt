package com.khrisna.filmdb.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khrisna.core.di.Injection
import com.khrisna.core.domain.usecase.FavoriteUseCase
import com.khrisna.core.domain.usecase.MovieUseCase
import com.khrisna.core.domain.usecase.SearchUseCase
import com.khrisna.core.domain.usecase.TVShowUseCase


class ViewModelFactory(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TVShowUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideMovieUseCase(context),
                    Injection.provideTVShowUseCase(context),
                    Injection.provideFavoriteUseCase(context),
                    Injection.provideSearchUseCase(context)
                )
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(
                movieUseCase, tvShowUseCase, favoriteUseCase
            ) as T
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(
                movieUseCase
            ) as T
            modelClass.isAssignableFrom(TVShowsViewModel::class.java) -> TVShowsViewModel(
                tvShowUseCase
            ) as T
            modelClass.isAssignableFrom(ViewAllViewModel::class.java) -> ViewAllViewModel(
                movieUseCase, tvShowUseCase
            ) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(
                searchUseCase
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}