package com.khrisna.core.domain.usecase

import com.khrisna.core.domain.model.Searches
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    fun search(query: String): Flow<Searches>
}