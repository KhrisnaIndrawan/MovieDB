package com.khrisna.core.domain.usecase

import androidx.paging.PagedList
import com.khrisna.core.domain.model.Favorite
import com.khrisna.core.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor(private val repository: IFavoriteRepository) : FavoriteUseCase {
    override fun getFavorite(id: Int): Flow<Favorite>? = repository.getFavorite(id)

    override fun getFavoritesAsPaged(isMovie: Boolean): Flow<PagedList<Favorite>> =
        repository.getFavoritesAsPaged(isMovie)

    override fun insertFavorite(data: Favorite) = repository.insertFavorite(data)

    override fun deleteFavorite(data: Favorite) = repository.deleteFavorite(data)

    override fun updateFavorite(data: Favorite) = repository.updateFavorite(data)
}