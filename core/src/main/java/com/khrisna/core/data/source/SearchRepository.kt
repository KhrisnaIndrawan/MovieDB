package com.khrisna.core.data.source

import com.khrisna.core.data.source.remote.RemoteDataSource
import com.khrisna.core.domain.model.Searches
import com.khrisna.core.domain.repository.ISearchRepository
import com.khrisna.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SearchRepository(
    val remoteDataSource: RemoteDataSource
) : ISearchRepository {

    override fun search(query: String): Flow<Searches> {
        return remoteDataSource.search(query).map {
            DataMapper.mapSearchesResponseToDomain(it)
        }
    }
}