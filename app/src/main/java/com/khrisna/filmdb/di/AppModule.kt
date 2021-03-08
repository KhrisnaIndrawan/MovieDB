package com.khrisna.filmdb.di

import com.khrisna.core.domain.usecase.*
import com.khrisna.filmdb.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TVShowUseCase> { TVShowInteractor(get()) }
    factory<FavoriteUseCase> { FavoriteInteractor(get()) }
    factory<SearchUseCase> { SearchInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailViewModel(get(), get(), get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { TVShowsViewModel(get()) }
    viewModel { ViewAllViewModel(get(), get()) }
}