package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.FavoriteEntity

class FavoritesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getFavorites(): LiveData<List<FavoriteEntity>> {
        return movieRepository.getFavorites()
    }
}