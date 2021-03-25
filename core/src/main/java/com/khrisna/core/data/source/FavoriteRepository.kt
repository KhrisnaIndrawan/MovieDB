package com.khrisna.core.data.source

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.khrisna.core.data.source.local.LocalDataSource
import com.khrisna.core.domain.model.Favorite
import com.khrisna.core.domain.repository.IFavoriteRepository
import com.khrisna.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class FavoriteRepository(
    val localDataSource: LocalDataSource
) : IFavoriteRepository {

    override fun getFavorite(id: Int): Flow<Favorite> {
        return localDataSource.getFavoriteById(id).map {
            DataMapper.mapFavoriteEntityToDomain(it)
        }
    }

    override fun getFavoritesAsPaged(isMovie: Boolean): Flow<PagedList<Favorite>> {
        return LivePagedListBuilder(
            localDataSource.getFavoritesAsPaged(isMovie).map {
                DataMapper.mapFavoriteEntityToDomain(it)
            }, 20
        ).build().asFlow()
    }

    override fun insertFavorite(data: Favorite) {
        Executors.newSingleThreadExecutor().execute {
            val favorite = DataMapper.mapFavoriteDomainToEntity(data)
            localDataSource.insertFavorite(favorite)
        }
    }

    override fun deleteFavorite(data: Favorite) {
        Executors.newSingleThreadExecutor().execute {
            val favorite = DataMapper.mapFavoriteDomainToEntity(data)
            localDataSource.deleteFavorite(favorite)
        }
    }

    override fun updateFavorite(data: Favorite) {
        Executors.newSingleThreadExecutor().execute {
            val favorite = DataMapper.mapFavoriteDomainToEntity(data)
            localDataSource.updateFavorite(favorite)
        }
    }
}