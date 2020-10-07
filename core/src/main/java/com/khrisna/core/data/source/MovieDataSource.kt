package com.khrisna.core.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.khrisna.core.data.source.local.entity.*
import com.khrisna.core.data.source.remote.response.SearchesResponse
import com.khrisna.core.data.source.vo.Resource

interface MovieDataSource {

    fun getFavorite(id: Int): LiveData<FavoriteEntity>?

    fun getFavoritesAsPaged(isMovie: Boolean): LiveData<PagedList<FavoriteEntity>>

    fun insertFavorite(favorite: FavoriteEntity)

    fun deleteFavorite(favorite: FavoriteEntity)

    fun updateFavorite(favorite: FavoriteEntity)

    fun search(query: String): LiveData<SearchesResponse>

    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getMovies(id: Int, header: String): LiveData<Resource<MoviesEntity>>

    fun getMoviesByHeader(header: String): LiveData<MoviesEntity>

    fun getTVShow(id: Int): LiveData<Resource<TVShowEntity>>

    fun getTVShows(id: Int, header: String): LiveData<Resource<TVShowsEntity>>

    fun getTVShowsByHeader(header: String): LiveData<TVShowsEntity>
}