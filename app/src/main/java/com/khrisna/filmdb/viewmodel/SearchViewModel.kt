package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khrisna.core.domain.model.Searches
import com.khrisna.core.domain.usecase.SearchUseCase

class SearchViewModel(
    val searchUseCase: SearchUseCase
) : ViewModel() {

    fun getSearches(query: String): LiveData<Searches> {
        return searchUseCase.search(query).asLiveData()
    }
}