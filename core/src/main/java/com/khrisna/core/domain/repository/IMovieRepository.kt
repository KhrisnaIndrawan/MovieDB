package com.khrisna.core.domain.repository

import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.Movie
import com.khrisna.core.domain.model.Movies
import com.khrisna.core.utils.ReleaseType
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovie(id: Int): Flow<Resource<Movie>>
    fun getMovies(id: Int, header: ReleaseType): Flow<Resource<Movies>>
    fun getMoviesByHeader(header: String): Flow<Movies>
}