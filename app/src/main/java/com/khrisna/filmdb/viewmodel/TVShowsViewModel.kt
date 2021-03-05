package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.TVShows
import com.khrisna.core.domain.usecase.TVShowUseCase
import com.khrisna.core.utils.ReleaseType

class TVShowsViewModel(
    private val tvShowUseCase: TVShowUseCase
) : ViewModel() {

    fun getAiringToday(): LiveData<Resource<TVShows>> {
        return tvShowUseCase.getTVShows(1, ReleaseType.AIRING_TODAY).asLiveData()
    }

    fun getOnTheAir(): LiveData<Resource<TVShows>> {
        return tvShowUseCase.getTVShows(2, ReleaseType.ON_THE_AIR).asLiveData()
    }

    fun getPopular(): LiveData<Resource<TVShows>> {
        return tvShowUseCase.getTVShows(3, ReleaseType.POPULAR).asLiveData()
    }

    fun getTopRated(): LiveData<Resource<TVShows>> {
        return tvShowUseCase.getTVShows(4, ReleaseType.TOP_RATED).asLiveData()
    }
}