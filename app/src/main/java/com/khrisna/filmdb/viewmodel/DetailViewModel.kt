package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.FavoriteEntity
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.vo.Resource

class DetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movie: LiveData<Resource<MovieEntity>>? = null
    private var _tvShow: LiveData<Resource<TVShowEntity>>? = null
    private var _favorite: LiveData<FavoriteEntity>? = null

    val favorite: LiveData<FavoriteEntity>?
        get() = _favorite
    val movie: LiveData<Resource<MovieEntity>>?
        get() = _movie
    val tvShow: LiveData<Resource<TVShowEntity>>?
        get() = _tvShow

    fun getFavorite(id: Int) {
        _favorite = movieRepository.getFavorite(id)
    }

    fun insertFavorite(favorite: FavoriteEntity) {
        movieRepository.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: FavoriteEntity) {
        movieRepository.deleteFavorite(favorite)
    }

    fun getMovie(id: Int) {
        _movie = movieRepository.getMovie(id)
    }

    fun getTVShow(id: Int) {
        _tvShow = movieRepository.getTVShow(id)
    }
}