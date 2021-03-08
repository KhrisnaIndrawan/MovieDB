package com.khrisna.core.data.source

import com.khrisna.core.data.source.local.LocalDataSource
import com.khrisna.core.data.source.remote.ApiResponse
import com.khrisna.core.data.source.remote.RemoteDataSource
import com.khrisna.core.data.source.remote.response.TVShowResponse
import com.khrisna.core.data.source.remote.response.TVShowsResponse
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.TVShow
import com.khrisna.core.domain.model.TVShows
import com.khrisna.core.domain.repository.ITVShowRepository
import com.khrisna.core.utils.DataMapper
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TVShowRepository(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) : ITVShowRepository {

    override fun getTVShow(id: Int): Flow<Resource<TVShow>> =
        object : NetworkBoundResource<TVShow, TVShowResponse>() {
            override fun loadFromDB(): Flow<TVShow> {
                return localDataSource.getTVShowById(id).map {
                    DataMapper.mapTVShowEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: TVShow?): Boolean =
                data != null

            override suspend fun createCall(): Flow<ApiResponse<TVShowResponse>> =
                remoteDataSource.getTVShow(id)

            override suspend fun saveCallResult(data: TVShowResponse) {
                val movie = DataMapper.mapTVShowResponseToEntity(data)
                movie?.let { localDataSource.insertTVShow(it) }
            }
        }.asFlow()

    override fun getTVShows(id: Int, header: ReleaseType): Flow<Resource<TVShows>> =
        object : NetworkBoundResource<TVShows, TVShowsResponse>() {
            override fun loadFromDB(): Flow<TVShows> {
                return localDataSource.getTVShowsById(id).map {
                    DataMapper.mapTVShowsEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: TVShows?): Boolean =
                data != null

            override suspend fun createCall(): Flow<ApiResponse<TVShowsResponse>> =
                remoteDataSource.getTVShows(header, "1")

            override suspend fun saveCallResult(data: TVShowsResponse) {
                val tvShows = DataMapper.mapTVShowsResponseToEntity(data, id)
                tvShows?.let { localDataSource.insertTVShows(it) }
            }
        }.asFlow()

    override fun getTVShowsByHeader(header: String): Flow<TVShows> =
        localDataSource.getTVShowsByHeader(header).map {
            DataMapper.mapTVShowsEntityToDomain(it)
        }
}