package com.khrisna.core.domain.repository

import com.khrisna.core.domain.model.Searches
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun search(query: String): Flow<Searches>
}