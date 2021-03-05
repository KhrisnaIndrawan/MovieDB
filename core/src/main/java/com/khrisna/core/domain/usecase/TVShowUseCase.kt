package com.khrisna.core.domain.usecase

import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.TVShow
import com.khrisna.core.domain.model.TVShows
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.flow.Flow

interface TVShowUseCase {
    fun getTVShow(id: Int): Flow<Resource<TVShow>>
    fun getTVShows(id: Int, header: ReleaseType): Flow<Resource<TVShows>>
    fun getTVShowsByHeader(header: String): Flow<TVShows>
}