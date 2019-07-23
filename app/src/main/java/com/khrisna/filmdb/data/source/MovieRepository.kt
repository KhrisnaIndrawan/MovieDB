package com.khrisna.filmdb.data.source

import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.local.LocalRepository
import com.khrisna.filmdb.data.source.remote.RemoteRepository
import com.khrisna.filmdb.data.source.remote.response.MovieResponse
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse

class MovieRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : MovieDataSource {

    override fun getMovie(id: String): MutableLiveData<MovieResponse> {
        return remoteRepository.getMovie(id)
    }

    override fun getMoviesNowPlaying(): MutableLiveData<MoviesResponse> {
        return remoteRepository.getMoviesNowPlaying()
    }

    override fun getMoviesUpComing(): MutableLiveData<MoviesResponse> {
        return remoteRepository.getMoviesUpComing()
    }

    override fun getMoviesPopular(): MutableLiveData<MoviesResponse> {
        return remoteRepository.getMoviesPopular()
    }

    override fun getMoviesTopRated(): MutableLiveData<MoviesResponse> {
        return remoteRepository.getMoviesTopRated()
    }

    override fun getTVShow(id: String): MutableLiveData<TVShowResponse> {
        return remoteRepository.getTVShow(id)
    }

    override fun getTVShowsAiringToday(): MutableLiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsAiringToday()
    }

    override fun getTVShowsOnTheAir(): MutableLiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsOnTheAir()
    }

    override fun getTVShowsPopular(): MutableLiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsPopular()
    }

    override fun getTVShowsTopRated(): MutableLiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsTopRated()
    }
}