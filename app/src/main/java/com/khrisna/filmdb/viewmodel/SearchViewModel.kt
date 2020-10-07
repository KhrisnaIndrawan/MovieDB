package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.core.data.source.MovieRepository
import com.khrisna.core.data.source.remote.response.SearchesResponse

class SearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getSearches(query: String): LiveData<SearchesResponse> {
        return movieRepository.search(query)
    }
}