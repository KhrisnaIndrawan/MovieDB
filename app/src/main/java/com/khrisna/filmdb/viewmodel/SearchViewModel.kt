package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.remote.response.SearchesResponse

class SearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getSearches(query: String): LiveData<SearchesResponse> {
        return movieRepository.search(query)
    }
}