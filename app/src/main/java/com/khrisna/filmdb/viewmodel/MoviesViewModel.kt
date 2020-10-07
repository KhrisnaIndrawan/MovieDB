package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.core.data.source.MovieRepository
import com.khrisna.core.data.source.local.entity.MoviesEntity
import com.khrisna.core.data.source.vo.Resource

class MoviesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getNowPlaying(): LiveData<Resource<MoviesEntity>> {
        return movieRepository.getMovies(1, "Now Playing")
    }

    fun getUpComing(): LiveData<Resource<MoviesEntity>> {
        return movieRepository.getMovies(2, "Up Coming")
    }

    fun getPopular(): LiveData<Resource<MoviesEntity>> {
        return movieRepository.getMovies(3, "Popular")
    }

    fun getTopRated(): LiveData<Resource<MoviesEntity>> {
        return movieRepository.getMovies(4, "Top Rated")
    }
}