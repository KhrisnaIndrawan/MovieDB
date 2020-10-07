package com.khrisna.core.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.khrisna.core.data.source.local.LocalRepository
import com.khrisna.core.data.source.local.entity.*
import com.khrisna.core.data.source.remote.ApiResponse
import com.khrisna.core.data.source.remote.RemoteRepository
import com.khrisna.core.data.source.remote.response.*
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.utils.AppExecutors
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

    override fun getFavorite(id: Int): LiveData<FavoriteEntity>? {
        return localRepository.getFavoriteById(id)
    }

    override fun getFavoritesAsPaged(isMovie: Boolean): LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder(
            localRepository.getFavoritesAsPaged(isMovie), 20
        ).build()
    }

    override fun insertFavorite(favorite: FavoriteEntity) {
        GlobalScope.launch {
            localRepository.insertFavorite(favorite)
        }
    }

    override fun deleteFavorite(favorite: FavoriteEntity) {
        GlobalScope.launch {
            localRepository.deleteFavorite(favorite)
        }
    }

    override fun updateFavorite(favorite: FavoriteEntity) {
        GlobalScope.launch {
            localRepository.updateFavorite(favorite)
        }
    }

    override fun search(query: String): LiveData<SearchesResponse> {
        return remoteRepository.search(query)
    }

    override fun getMovie(id: Int): LiveData<Resource<MovieEntity>> {
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

    override fun getMovies(id: Int, header: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localRepository.getMoviesById(id)
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return (data == null) || (data.movies == null) || (data.movies?.size == 0)
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                return remoteRepository.getMovies(header, "1")
            }

            override fun saveCallResult(data: MoviesResponse) {
                val movieEntityList = mutableListOf<MovieEntity>()

                for (movie in data.movies) {

                    val movieEntity = parseToMovieEntity(movie) as MovieEntity
                    movieEntityList.add(movieEntity)
                }

                val moviesEntity = MoviesEntity(
                    id = id,
                    header = header,
                    movies = movieEntityList
                )

                localRepository.insertMovies(moviesEntity)
            }
        }.asLiveData()
    }

    override fun getMoviesByHeader(header: String): LiveData<MoviesEntity> {
        return localRepository.getMoviesByHeader(header)
    }

    override fun getTVShow(id: Int): LiveData<Resource<TVShowEntity>> {
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

    override fun getTVShows(id: Int, header: String): LiveData<Resource<TVShowsEntity>> {
        return object : NetworkBoundResource<TVShowsEntity, TVShowsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TVShowsEntity> {
                return localRepository.getTVShowsById(id)
            }

            override fun shouldFetch(data: TVShowsEntity?): Boolean {
                return (data == null) || (data.tvShows == null) || (data.tvShows?.size == 0)
            }

            override fun createCall(): LiveData<ApiResponse<TVShowsResponse>> {
                return remoteRepository.getTVShows(header, "1")
            }

            override fun saveCallResult(data: TVShowsResponse) {
                val tvShowEntityList = mutableListOf<TVShowEntity>()
                for (tvShow in data.tvShows) {

                    val tvShowEntity = parseToTVShowEntity(tvShow) as TVShowEntity
                    tvShowEntityList.add(tvShowEntity)
                }

                val tvShowsEntity = TVShowsEntity(
                    id = id,
                    header = header,
                    tvShows = tvShowEntityList
                )

                localRepository.insertTVShows(tvShowsEntity)
            }
        }.asLiveData()
    }

    override fun getTVShowsByHeader(header: String): LiveData<TVShowsEntity> {
        return localRepository.getTVShowsByHeader(header)
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