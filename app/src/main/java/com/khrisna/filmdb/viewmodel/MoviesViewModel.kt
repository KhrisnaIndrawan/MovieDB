package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.Movies
import com.khrisna.core.domain.usecase.MovieUseCase
import com.khrisna.core.utils.ReleaseType

class MoviesViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    fun getNowPlaying(): LiveData<Resource<Movies>> {
        return movieUseCase.getMovies(1, ReleaseType.NOW_PLAYING).asLiveData()
    }

    fun getUpComing(): LiveData<Resource<Movies>> {
        return movieUseCase.getMovies(2, ReleaseType.UP_COMING).asLiveData()
    }

    fun getPopular(): LiveData<Resource<Movies>> {
        return movieUseCase.getMovies(3, ReleaseType.POPULAR).asLiveData()
    }

    fun getTopRated(): LiveData<Resource<Movies>> {
        return movieUseCase.getMovies(4, ReleaseType.TOP_RATED).asLiveData()
    }
}