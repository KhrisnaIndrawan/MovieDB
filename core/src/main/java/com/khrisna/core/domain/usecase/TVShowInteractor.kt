package com.khrisna.core.domain.usecase

import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.TVShow
import com.khrisna.core.domain.model.TVShows
import com.khrisna.core.domain.repository.ITVShowRepository
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.flow.Flow

class TVShowInteractor(private val repository: ITVShowRepository) : TVShowUseCase {
    override fun getTVShow(id: Int): Flow<Resource<TVShow>> = repository.getTVShow(id)

    override fun getTVShows(id: Int, header: ReleaseType): Flow<Resource<TVShows>> =
        repository.getTVShows(id, header)

    override fun getTVShowsByHeader(header: String): Flow<TVShows> =
        repository.getTVShowsByHeader(header)
}