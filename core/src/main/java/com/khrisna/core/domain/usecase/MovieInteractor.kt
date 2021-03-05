package com.khrisna.core.domain.usecase

import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.Movie
import com.khrisna.core.domain.model.Movies
import com.khrisna.core.domain.repository.IMovieRepository
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val repository: IMovieRepository) : MovieUseCase {
    override fun getMovie(id: Int): Flow<Resource<Movie>> = repository.getMovie(id)

    override fun getMovies(id: Int, header: ReleaseType): Flow<Resource<Movies>> =
        repository.getMovies(id, header)

    override fun getMoviesByHeader(header: String): Flow<Movies> =
        repository.getMoviesByHeader(header)
}