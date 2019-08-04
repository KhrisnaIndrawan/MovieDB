package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import com.khrisna.filmdb.data.source.local.LocalRepository
import com.khrisna.filmdb.data.source.local.entity.*
import com.khrisna.filmdb.data.source.remote.ApiResponse
import com.khrisna.filmdb.data.source.remote.RemoteRepository
import com.khrisna.filmdb.data.source.remote.response.*
import com.khrisna.filmdb.data.source.vo.Resource
import com.khrisna.filmdb.utils.AppExecutors

class MovieRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        private var INSTANCE: MovieRepository? = null

        fun getInstance(
            local: LocalRepository,
            remote: RemoteRepository,
            appExecutors: AppExecutors
        ): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class) {
                    INSTANCE = MovieRepository(local, remote, appExecutors)
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getMovie(id: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<MovieEntity> {
                return localRepository.getMovieById(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteRepository.getMovie(id)
            }

            override fun saveCallResult(data: MovieResponse) {
                val genreResponse = data.genres
                val genreEntityList = mutableListOf<GenreEntity>()
                for (genre in genreResponse) {
                    genreEntityList.add(
                        parseToGenre(genre)
                    )
                }

                val movieEntity = parseToMovieEntityWithGenres(data, genreEntityList)

                localRepository.insertMovie(movieEntity as MovieEntity)
            }
        }.asLiveData()
    }

    override fun getMoviesNowPlaying(page: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localRepository.getMoviesByHeader("Now Playing")
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                return remoteRepository.getMoviesNowPlaying(page)
            }

            override fun saveCallResult(data: MoviesResponse) {
                val movieEntityList = mutableListOf<MovieEntity>()

                for (movie in data.movies) {

                    val movieEntity = parseToMovieEntity(movie) as MovieEntity
                    movieEntityList.add(movieEntity)
                }

                val moviesEntity = MoviesEntity(
                    id = 1,
                    header = "Now Playing",
                    movies = movieEntityList
                )

                localRepository.insertMovies(moviesEntity)
            }
        }.asLiveData()
    }

    override fun getMoviesUpComing(page: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localRepository.getMoviesByHeader("Up Coming")
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                return remoteRepository.getMoviesUpComing(page)
            }

            override fun saveCallResult(data: MoviesResponse) {
                val movieEntityList = mutableListOf<MovieEntity>()

                for (movie in data.movies) {

                    val movieEntity = parseToMovieEntity(movie) as MovieEntity
                    movieEntityList.add(movieEntity)
                }

                val moviesEntity = MoviesEntity(
                    id = 1,
                    header = "Up Coming",
                    movies = movieEntityList
                )

                localRepository.insertMovies(moviesEntity)
            }
        }.asLiveData()
    }

    override fun getMoviesPopular(page: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localRepository.getMoviesByHeader("Popular")
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                return remoteRepository.getMoviesPopular(page)
            }

            override fun saveCallResult(data: MoviesResponse) {
                val movieEntityList = mutableListOf<MovieEntity>()

                for (movie in data.movies) {

                    val movieEntity = parseToMovieEntity(movie) as MovieEntity
                    movieEntityList.add(movieEntity)
                }

                val moviesEntity = MoviesEntity(
                    id = 1,
                    header = "Popular",
                    movies = movieEntityList
                )

                localRepository.insertMovies(moviesEntity)
            }
        }.asLiveData()
    }

    override fun getMoviesTopRated(page: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localRepository.getMoviesByHeader("Top Rated")
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                return remoteRepository.getMoviesTopRated(page)
            }

            override fun saveCallResult(data: MoviesResponse) {
                val movieEntityList = mutableListOf<MovieEntity>()

                for (movie in data.movies) {

                    val movieEntity = parseToMovieEntity(movie) as MovieEntity
                    movieEntityList.add(movieEntity)
                }

                val moviesEntity = MoviesEntity(
                    id = 1,
                    header = "Top Rated",
                    movies = movieEntityList
                )

                localRepository.insertMovies(moviesEntity)
            }
        }.asLiveData()
    }

    override fun getTVShow(id: String): LiveData<Resource<TVShowEntity>> {
        return object : NetworkBoundResource<TVShowEntity, TVShowResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<TVShowEntity> {
                return localRepository.getTVShowById(id)
            }

            override fun shouldFetch(data: TVShowEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<TVShowResponse>> {
                return remoteRepository.getTVShow(id)
            }

            override fun saveCallResult(data: TVShowResponse) {
                val genreResponse = data.genres
                val genreEntityList = mutableListOf<GenreEntity>()
                for (genre in genreResponse) {
                    genreEntityList.add(
                        parseToGenre(genre)
                    )
                }

                val tvShowEntity = parseToTVShowEntityWithGenres(data, genreEntityList)

                localRepository.insertTVShow(tvShowEntity as TVShowEntity)
            }
        }.asLiveData()
    }

    override fun getTVShowsAiringToday(page: String): LiveData<Resource<TVShowsEntity>> {
        return object : NetworkBoundResource<TVShowsEntity, TVShowsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TVShowsEntity> {
                return localRepository.getTVShowsByHeader("Airing Today")
            }

            override fun shouldFetch(data: TVShowsEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<TVShowsResponse>> {
                return remoteRepository.getTVShowsAiringToday(page)
            }

            override fun saveCallResult(data: TVShowsResponse) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()
                for (tvShow in data.tvShows) {

                    val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                    tvShowEntityList.add(tvShowEntity)
                }

                val tvShowsEntity = TVShowsEntity(
                    id = 1,
                    header = "Airing Today",
                    tvShows = tvShowEntityList
                )

                localRepository.insertTVShows(tvShowsEntity)
            }
        }.asLiveData()
    }

    override fun getTVShowsOnTheAir(page: String): LiveData<Resource<TVShowsEntity>> {
        return object : NetworkBoundResource<TVShowsEntity, TVShowsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TVShowsEntity> {
                return localRepository.getTVShowsByHeader("On The Air")
            }

            override fun shouldFetch(data: TVShowsEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<TVShowsResponse>> {
                return remoteRepository.getTVShowsOnTheAir(page)
            }

            override fun saveCallResult(data: TVShowsResponse) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()
                for (tvShow in data.tvShows) {

                    val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                    tvShowEntityList.add(tvShowEntity)
                }

                val tvShowsEntity = TVShowsEntity(
                    id = 1,
                    header = "On The Air",
                    tvShows = tvShowEntityList
                )

                localRepository.insertTVShows(tvShowsEntity)
            }
        }.asLiveData()
    }

    override fun getTVShowsPopular(page: String): LiveData<Resource<TVShowsEntity>> {
        return object : NetworkBoundResource<TVShowsEntity, TVShowsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TVShowsEntity> {
                return localRepository.getTVShowsByHeader("Popular")
            }

            override fun shouldFetch(data: TVShowsEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<TVShowsResponse>> {
                return remoteRepository.getTVShowsPopular(page)
            }

            override fun saveCallResult(data: TVShowsResponse) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()
                for (tvShow in data.tvShows) {

                    val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                    tvShowEntityList.add(tvShowEntity)
                }

                val tvShowsEntity = TVShowsEntity(
                    id = 1,
                    header = "Popular",
                    tvShows = tvShowEntityList
                )

                localRepository.insertTVShows(tvShowsEntity)
            }
        }.asLiveData()
    }

    override fun getTVShowsTopRated(page: String): LiveData<Resource<TVShowsEntity>> {
        return object : NetworkBoundResource<TVShowsEntity, TVShowsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TVShowsEntity> {
                return localRepository.getTVShowsByHeader("Top Rated")
            }

            override fun shouldFetch(data: TVShowsEntity?): Boolean {
                return (data == null)
            }

            override fun createCall(): LiveData<ApiResponse<TVShowsResponse>> {
                return remoteRepository.getTVShowsTopRated(page)
            }

            override fun saveCallResult(data: TVShowsResponse) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()
                for (tvShow in data.tvShows) {

                    val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                    tvShowEntityList.add(tvShowEntity)
                }

                val tvShowsEntity = TVShowsEntity(
                    id = 1,
                    header = "Top Rated",
                    tvShows = tvShowEntityList
                )

                localRepository.insertTVShows(tvShowsEntity)
            }
        }.asLiveData()
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