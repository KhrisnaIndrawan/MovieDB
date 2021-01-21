package com.khrisna.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.khrisna.core.data.source.MovieRepository
import com.khrisna.core.data.source.local.entity.FavoriteEntity

class FavoritesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getFavoritesAsPaged(isMovie: Boolean): LiveData<PagedList<FavoriteEntity>> {
        return movieRepository.getFavoritesAsPaged(isMovie)
    }
}