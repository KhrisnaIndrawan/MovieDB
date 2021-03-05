package com.khrisna.core.domain.usecase

import com.khrisna.core.domain.model.Searches
import com.khrisna.core.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class SearchInteractor(private val repository: ISearchRepository) : SearchUseCase {
    override fun search(query: String): Flow<Searches> = repository.search(query)
}