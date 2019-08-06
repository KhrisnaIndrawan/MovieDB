package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.FavoriteEntity

class FavoritesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _favorites: LiveData<List<FavoriteEntity>>? = null

    val favorites: LiveData<List<FavoriteEntity>>?
        get() = _favorites

    fun getFavorites() {
        _favorites = movieRepository.getFavorites()
    }
}