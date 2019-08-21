package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.data.source.vo.Resource

class TVShowsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getAiringToday(): LiveData<Resource<TVShowsEntity>> {
        return movieRepository.getTVShows(1, "Airing Today")
    }

    fun getOnTheAir(): LiveData<Resource<TVShowsEntity>> {
        return movieRepository.getTVShows(2, "On The Air")
    }

    fun getPopular(): LiveData<Resource<TVShowsEntity>> {
        return movieRepository.getTVShows(3, "Popular")
    }

    fun getTopRated(): LiveData<Resource<TVShowsEntity>> {
        return movieRepository.getTVShows(4, "Top Rated")
    }
}