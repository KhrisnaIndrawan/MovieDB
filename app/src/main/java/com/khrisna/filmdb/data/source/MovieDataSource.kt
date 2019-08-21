package com.khrisna.filmdb.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.khrisna.filmdb.data.source.local.entity.*
import com.khrisna.filmdb.data.source.remote.response.SearchesResponse
import com.khrisna.filmdb.data.source.vo.Resource

interface MovieDataSource {

    fun getFavorite(id: Int): LiveData<FavoriteEntity>?

    fun getFavoritesAsPaged(isMovie: Boolean): LiveData<PagedList<FavoriteEntity>>

    fun insertFavorite(favorite: FavoriteEntity)

    fun deleteFavorite(favorite: FavoriteEntity)

    fun updateFavorite(favorite: FavoriteEntity)

    fun search(query: String): LiveData<SearchesResponse>

    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getMovies(id: Int, header: String): LiveData<Resource<MoviesEntity>>

    fun getTVShow(id: Int): LiveData<Resource<TVShowEntity>>

    fun getTVShows(id: Int, header: String): LiveData<Resource<TVShowsEntity>>
}