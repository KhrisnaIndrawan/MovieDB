package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.khrisna.filmdb.data.source.local.LocalRepository
import com.khrisna.filmdb.data.source.local.entity.GenreEntity
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.remote.RemoteRepository
import com.khrisna.filmdb.data.source.remote.response.*

class MovieRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : MovieDataSource {

    override fun getMovie(id: String): LiveData<MovieEntity> {

        return Transformations.switchMap(
            remoteRepository.getMovie(id)
        ) {
            switchToMovieEntity(it)
        }
    }

    private fun switchToMovieEntity(response: MovieResponse): LiveData<MovieEntity> {

        val movie = MutableLiveData<MovieEntity>()
        val genreResponse = response.genreResponses
        val genreEntityList = mutableListOf<GenreEntity>()
        for (genre in genreResponse) {
            genreEntityList.add(
                parseToGenre(genre)
            )
        }

        movie.postValue(
            parseToMovie(
                response, genreEntityList
            )
        )

        return MutableLiveData()
    }

    override fun getMoviesNowPlaying(): LiveData<MoviesResponse> {
        return remoteRepository.getMoviesNowPlaying()
    }

    override fun getMoviesUpComing(): LiveData<MoviesResponse> {
        return remoteRepository.getMoviesUpComing()
    }

    override fun getMoviesPopular(): LiveData<MoviesResponse> {
        return remoteRepository.getMoviesPopular()
    }

    override fun getMoviesTopRated(): LiveData<MoviesResponse> {
        return remoteRepository.getMoviesTopRated()
    }

    override fun getTVShow(id: String): LiveData<TVShowEntity> {
        return Transformations.switchMap(
            remoteRepository.getTVShow(id)
        ) {
            switchToTVShowEntity(it)
        }
    }

    private fun switchToTVShowEntity(response: TVShowResponse): LiveData<TVShowEntity> {

        val tvShow = MutableLiveData<TVShowEntity>()
        val genreResponse = response.genreResponses
        val genreEntityList = mutableListOf<GenreEntity>()
        for (genre in genreResponse) {
            genreEntityList.add(
                parseToGenre(genre)
            )
        }

        tvShow.postValue(
            parseToTVShow(
                response, genreEntityList
            )
        )

        return MutableLiveData()
    }

    override fun getTVShowsAiringToday(): LiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsAiringToday()
    }

    override fun getTVShowsOnTheAir(): LiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsOnTheAir()
    }

    override fun getTVShowsPopular(): LiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsPopular()
    }

    override fun getTVShowsTopRated(): LiveData<TVShowsResponse> {
        return remoteRepository.getTVShowsTopRated()
    }

    private fun parseToMovie(response: MovieResponse, genreEntityList: MutableList<GenreEntity>): MovieEntity? {
        return MovieEntity(
            id = response.id,
            title = response.title,
            overview = response.overview,
            poster = response.poster,
            backdrop = response.backdrop,
            releaseDate = response.releaseDate,
            rating = response.rating,
            genreResponses = genreEntityList
        )
    }

    private fun parseToTVShow(response: TVShowResponse, genreEntityList: MutableList<GenreEntity>): TVShowEntity? {
        return TVShowEntity(
            id = response.id,
            title = response.title,
            overview = response.overview,
            poster = response.poster,
            backdrop = response.backdrop,
            firstAir = response.firstAir,
            rating = response.rating,
            genreResponses = genreEntityList
        )
    }

    private fun parseToGenre(genre: GenreResponse): GenreEntity {
        return GenreEntity(
            id = genre.id,
            name = genre.name
        )
    }
}