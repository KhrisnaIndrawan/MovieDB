package com.khrisna.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagedList
import com.khrisna.core.domain.model.Favorite
import com.khrisna.core.domain.usecase.FavoriteUseCase

class FavoritesViewModel(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    fun getFavoritesAsPaged(isMovie: Boolean): LiveData<PagedList<Favorite>> {
        return favoriteUseCase.getFavoritesAsPaged(isMovie).asLiveData()
    }
}