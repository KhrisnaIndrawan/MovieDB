package com.khrisna.core.data.source

import com.khrisna.core.data.source.local.LocalDataSource
import com.khrisna.core.data.source.remote.ApiResponse
import com.khrisna.core.data.source.remote.RemoteDataSource
import com.khrisna.core.data.source.remote.response.MovieResponse
import com.khrisna.core.data.source.remote.response.MoviesResponse
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.Movie
import com.khrisna.core.domain.model.Movies
import com.khrisna.core.domain.repository.IMovieRepository
import com.khrisna.core.utils.DataMapper
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            localData: LocalDataSource,
            remoteData: RemoteDataSource
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(localData, remoteData)
            }
    }

    override fun getMovie(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(id).map {
                    DataMapper.mapMovieEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data != null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovie(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.mapMovieResponseToEntity(data)
                movie?.let { localDataSource.insertMovie(it) }
            }
        }.asFlow()

    override fun getMovies(id: Int, header: ReleaseType): Flow<Resource<Movies>> =
        object : NetworkBoundResource<Movies, MoviesResponse>() {
            override fun loadFromDB(): Flow<Movies> {
                return localDataSource.getMoviesById(id).map {
                    DataMapper.mapMoviesEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movies?): Boolean =
                data != null

            override suspend fun createCall(): Flow<ApiResponse<MoviesResponse>> =
                remoteDataSource.getMovies(header, "1")

            override suspend fun saveCallResult(data: MoviesResponse) {
                val movies = DataMapper.mapMoviesResponseToEntity(data, id)
                movies.let {
                    localDataSource.insertMovies(movies)
                }
            }
        }.asFlow()

    override fun getMoviesByHeader(header: String): Flow<Movies> =
        localDataSource.getMoviesByHeader(header).map {
            DataMapper.mapMoviesEntityToDomain(it)
        }
}