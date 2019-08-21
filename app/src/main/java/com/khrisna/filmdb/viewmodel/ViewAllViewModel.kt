package com.khrisna.filmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.data.source.vo.Resource

class ViewAllViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movies: LiveData<Resource<MoviesEntity>>? = null
    private var _tvShows: LiveData<Resource<TVShowsEntity>>? = null

    val movies: LiveData<Resource<MoviesEntity>>?
        get() = _movies
    val tvShows: LiveData<Resource<TVShowsEntity>>?
        get() = _tvShows

    fun getMovies(header: String, page: String) {
//        when (header) {
//            "Now Playing" -> {
//                _movies = movieRepository.getMoviesNowPlaying(page)
//            }
//            "Up Coming" -> {
//                _movies = movieRepository.getMoviesUpComing(page)
//            }
//            "Popular" -> {
//                _movies = movieRepository.getMoviesPopular(page)
//            }
//            "Top Rated" -> {
//                _movies = movieRepository.getMoviesTopRated(page)
//            }
//        }
    }

    fun getTVShows(header: String, page: String) {
//        when (header) {
//            "Airing Today" -> {
//                _tvShows = movieRepository.getTVShowsAiringToday(page)
//            }
//            "On The Air" -> {
//                _tvShows = movieRepository.getTVShowsOnTheAir(page)
//            }
//            "Popular" -> {
//                _tvShows = movieRepository.getTVShowsPopular(page)
//            }
//            "Top Rated" -> {
//                _tvShows = movieRepository.getTVShowsTopRated(page)
//            }
//        }
    }
}