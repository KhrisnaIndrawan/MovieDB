package com.khrisna.core.domain.usecase

import androidx.paging.PagedList
import com.khrisna.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getFavorite(id: Int): Flow<Favorite>?
    fun getFavoritesAsPaged(isMovie: Boolean): Flow<PagedList<Favorite>>
    fun insertFavorite(data: Favorite)
    fun deleteFavorite(data: Favorite)
    fun updateFavorite(data: Favorite)
}