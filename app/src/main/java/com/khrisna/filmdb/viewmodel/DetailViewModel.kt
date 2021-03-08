package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.Favorite
import com.khrisna.core.domain.model.Movie
import com.khrisna.core.domain.model.TVShow
import com.khrisna.core.domain.usecase.FavoriteUseCase
import com.khrisna.core.domain.usecase.MovieUseCase
import com.khrisna.core.domain.usecase.TVShowUseCase

class DetailViewModel(
    val movieUseCase: MovieUseCase,
    val tvShowUseCase: TVShowUseCase,
    val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private var _movie: LiveData<Resource<Movie>>? = null
    private var _tvShow: LiveData<Resource<TVShow>>? = null
    private var _favorite: LiveData<Favorite>? = null

    val favorite: LiveData<Favorite>?
        get() = _favorite
    val movie: LiveData<Resource<Movie>>?
        get() = _movie
    val tvShow: LiveData<Resource<TVShow>>?
        get() = _tvShow

    fun getFavorite(id: Int) {
        _favorite = favoriteUseCase.getFavorite(id)?.asLiveData()
    }

    fun insertFavorite(favorite: Favorite) {
        favoriteUseCase.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite) {
        favoriteUseCase.deleteFavorite(favorite)
    }

    fun getMovie(id: Int) {
        _movie = movieUseCase.getMovie(id).asLiveData()
    }

    fun getTVShow(id: Int) {
        _tvShow = tvShowUseCase.getTVShow(id).asLiveData()
    }
}