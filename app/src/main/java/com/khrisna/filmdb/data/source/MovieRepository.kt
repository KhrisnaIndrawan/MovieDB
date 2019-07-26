package com.khrisna.filmdb.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.local.LocalRepository
import com.khrisna.filmdb.data.source.local.entity.*
import com.khrisna.filmdb.data.source.remote.RemoteRepository
import com.khrisna.filmdb.data.source.remote.response.*

class MovieRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : MovieDataSource {

    override fun getMovie(id: String): LiveData<MovieEntity> {
        val movie = MutableLiveData<MovieEntity>()

        remoteRepository.getMovie(id, object : RemoteRepository.LoadMovieCallback {
            override fun onResponse(response: MovieResponse?) {
                if (response != null) {
                    val genreResponse = response.genres
                    val genreEntityList = mutableListOf<GenreEntity>()
                    for (genre in genreResponse) {
                        genreEntityList.add(
                            parseToGenre(genre)
                        )
                    }

                    val movieEntity = parseToMovieEntityWithGenres(response, genreEntityList)

                    movie.postValue(movieEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movie data failed.")
            }
        })

        return movie
    }

    override fun getMoviesNowPlaying(): LiveData<MoviesEntity> {
        val movies = MutableLiveData<MoviesEntity>()

        remoteRepository.getMoviesNowPlaying(object : RemoteRepository.LoadMoviesCallback {
            override fun onResponse(response: MoviesResponse?) {
                val movieEntityList = mutableListOf<MovieEntity>()

                if (response != null) {
                    for (movie in response.movies) {

                        val movieEntity = parseToMovieEntity(movie) as MovieEntity
                        movieEntityList.add(movieEntity)
                    }

                    val moviesEntity = MoviesEntity(
                        header = "Now Playing",
                        movies = movieEntityList
                    )
                    movies.postValue(moviesEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return movies
    }

    override fun getMoviesUpComing(): LiveData<MoviesEntity> {
        val movies = MutableLiveData<MoviesEntity>()

        remoteRepository.getMoviesUpComing(object : RemoteRepository.LoadMoviesCallback {
            override fun onResponse(response: MoviesResponse?) {
                val movieEntityList = mutableListOf<MovieEntity>()

                if (response != null) {
                    for (movie in response.movies) {

                        val movieEntity = parseToMovieEntity(movie) as MovieEntity
                        movieEntityList.add(movieEntity)
                    }

                    val moviesEntity = MoviesEntity(
                        header = "Up Coming",
                        movies = movieEntityList
                    )
                    movies.postValue(moviesEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return movies
    }

    override fun getMoviesPopular(): LiveData<MoviesEntity> {
        val movies = MutableLiveData<MoviesEntity>()

        remoteRepository.getMoviesPopular(object : RemoteRepository.LoadMoviesCallback {
            override fun onResponse(response: MoviesResponse?) {
                val movieEntityList = mutableListOf<MovieEntity>()

                if (response != null) {
                    for (movie in response.movies) {

                        val movieEntity = parseToMovieEntity(movie) as MovieEntity
                        movieEntityList.add(movieEntity)
                    }

                    val moviesEntity = MoviesEntity(
                        header = "Popular",
                        movies = movieEntityList
                    )
                    movies.postValue(moviesEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return movies
    }

    override fun getMoviesTopRated(): LiveData<MoviesEntity> {
        val movies = MutableLiveData<MoviesEntity>()

        remoteRepository.getMoviesTopRated(object : RemoteRepository.LoadMoviesCallback {
            override fun onResponse(response: MoviesResponse?) {
                val movieEntityList = mutableListOf<MovieEntity>()

                if (response != null) {
                    for (movie in response.movies) {

                        val movieEntity = parseToMovieEntity(movie) as MovieEntity
                        movieEntityList.add(movieEntity)
                    }

                    val moviesEntity = MoviesEntity(
                        header = "Top Rated",
                        movies = movieEntityList
                    )
                    movies.postValue(moviesEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return movies
    }

    override fun getTVShow(id: String): LiveData<TVShowEntity> {
        val tvShow = MutableLiveData<TVShowEntity>()

        remoteRepository.getTVShow(id, object : RemoteRepository.LoadTVShowCallback {
            override fun onResponse(response: TVShowResponse?) {
                if (response != null) {
                    val genreResponse = response.genres
                    val genreEntityList = mutableListOf<GenreEntity>()
                    for (genre in genreResponse) {
                        genreEntityList.add(
                            parseToGenre(genre)
                        )
                    }

                    val tvShowEntity = parseToTVShowEntityWithGenres(response, genreEntityList)

                    tvShow.postValue(tvShowEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get tvShow data failed.")
            }
        })

        return tvShow
    }

    override fun getTVShowsAiringToday(): LiveData<TVShowsEntity> {
        val tvShows = MutableLiveData<TVShowsEntity>()

        remoteRepository.getTVShowsAiringToday(object : RemoteRepository.LoadTVShowsCallback {
            override fun onResponse(response: TVShowsResponse?) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()

                if (response != null) {
                    for (tvShow in response.tvShows) {

                        val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                        tvShowEntityList.add(tvShowEntity)
                    }

                    val tvShowsEntity = TVShowsEntity(
                        header = "Airing Today",
                        tvShow = tvShowEntityList
                    )
                    tvShows.postValue(tvShowsEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return tvShows
    }

    override fun getTVShowsOnTheAir(): LiveData<TVShowsEntity> {
        val tvShows = MutableLiveData<TVShowsEntity>()

        remoteRepository.getTVShowsOnTheAir(object : RemoteRepository.LoadTVShowsCallback {
            override fun onResponse(response: TVShowsResponse?) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()

                if (response != null) {
                    for (tvShow in response.tvShows) {

                        val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                        tvShowEntityList.add(tvShowEntity)
                    }

                    val tvShowsEntity = TVShowsEntity(
                        header = "On The Air",
                        tvShow = tvShowEntityList
                    )
                    tvShows.postValue(tvShowsEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return tvShows
    }

    override fun getTVShowsPopular(): LiveData<TVShowsEntity> {
        val tvShows = MutableLiveData<TVShowsEntity>()

        remoteRepository.getTVShowsPopular(object : RemoteRepository.LoadTVShowsCallback {
            override fun onResponse(response: TVShowsResponse?) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()

                if (response != null) {
                    for (tvShow in response.tvShows) {

                        val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                        tvShowEntityList.add(tvShowEntity)
                    }

                    val tvShowsEntity = TVShowsEntity(
                        header = "Popular",
                        tvShow = tvShowEntityList
                    )
                    tvShows.postValue(tvShowsEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return tvShows
    }

    override fun getTVShowsTopRated(): LiveData<TVShowsEntity> {
        val tvShows = MutableLiveData<TVShowsEntity>()

        remoteRepository.getTVShowsTopRated(object : RemoteRepository.LoadTVShowsCallback {
            override fun onResponse(response: TVShowsResponse?) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()

                if (response != null) {
                    for (tvShow in response.tvShows) {

                        val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                        tvShowEntityList.add(tvShowEntity)
                    }

                    val tvShowsEntity = TVShowsEntity(
                        header = "Top Rated",
                        tvShow = tvShowEntityList
                    )
                    tvShows.postValue(tvShowsEntity)
                }
            }

            override fun onFailure() {
                Log.d("onFailure", "Get movies now playing failed!")
            }
        })

        return tvShows
    }

    private fun parseToMovieEntity(
        movieResponse: MovieResponse
    ): MovieEntity? {
        return MovieEntity(
            id = movieResponse.id,
            title = movieResponse.title,
            overview = movieResponse.overview,
            poster = movieResponse.poster,
            backdrop = movieResponse.backdrop,
            genres = mutableListOf(),
            rating = movieResponse.rating,
            releaseDate = movieResponse.releaseDate
        )
    }

    private fun parseToMovieEntityWithGenres(
        movieResponse: MovieResponse,
        genreEntityList: MutableList<GenreEntity>
    ): MovieEntity? {
        return MovieEntity(
            id = movieResponse.id,
            title = movieResponse.title,
            overview = movieResponse.overview,
            poster = movieResponse.poster,
            backdrop = movieResponse.backdrop,
            genres = genreEntityList,
            rating = movieResponse.rating,
            releaseDate = movieResponse.releaseDate
        )
    }

    private fun parseToTVShowEntity(
        tvShowResponse: TVShowResponse
    ): TVShowEntity? {
        return TVShowEntity(
            id = tvShowResponse.id,
            title = tvShowResponse.title,
            overview = tvShowResponse.overview,
            poster = tvShowResponse.poster,
            backdrop = tvShowResponse.backdrop,
            genres = mutableListOf(),
            rating = tvShowResponse.rating,
            firstAir = tvShowResponse.firstAir
        )
    }

    private fun parseToTVShowEntityWithGenres(
        tvShowResponse: TVShowResponse,
        genreEntityList: MutableList<GenreEntity>
    ): TVShowEntity? {
        return TVShowEntity(
            id = tvShowResponse.id,
            title = tvShowResponse.title,
            overview = tvShowResponse.overview,
            poster = tvShowResponse.poster,
            backdrop = tvShowResponse.backdrop,
            genres = genreEntityList,
            rating = tvShowResponse.rating,
            firstAir = tvShowResponse.firstAir
        )
    }

    private fun parseToGenre(genre: GenreResponse): GenreEntity {
        return GenreEntity(
            id = genre.id,
            name = genre.name
        )
    }
}