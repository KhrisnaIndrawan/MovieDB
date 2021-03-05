package com.khrisna.core.data.source

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.khrisna.core.data.source.local.LocalDataSource
import com.khrisna.core.domain.model.Favorite
import com.khrisna.core.domain.repository.IFavoriteRepository
import com.khrisna.core.utils.DataMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteRepository(
    private val localDataSource: LocalDataSource
) : IFavoriteRepository {

    companion object {
        private var INSTANCE: FavoriteRepository? = null

        fun getInstance(
            localData: LocalDataSource
        ): FavoriteRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: FavoriteRepository(localData)
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

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
        GlobalScope.launch {
            val favorite = DataMapper.mapFavoriteDomainToEntity(data)
            localDataSource.insertFavorite(favorite)
        }
    }

    override fun deleteFavorite(data: Favorite) {
        GlobalScope.launch {
            val favorite = DataMapper.mapFavoriteDomainToEntity(data)
            localDataSource.deleteFavorite(favorite)
        }
    }

    override fun updateFavorite(data: Favorite) {
        GlobalScope.launch {
            val favorite = DataMapper.mapFavoriteDomainToEntity(data)
            localDataSource.updateFavorite(favorite)
        }
    }
}