package com.khrisna.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khrisna.core.data.source.remote.network.RetrofitServices
import com.khrisna.core.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository(private val retrofitServices: RetrofitServices) {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(client: RetrofitServices): RemoteRepository? {
            if (INSTANCE == null) {
                synchronized(RemoteRepository::class) {
                    INSTANCE = RemoteRepository(client)
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    fun search(query: String): LiveData<SearchesResponse> {
        val searchResponse = MutableLiveData<SearchesResponse>()
        val networkServices = retrofitServices.createSearchService()

        networkServices
            .search(query)
            .enqueue(object : Callback<SearchesResponse> {
                override fun onFailure(call: Call<SearchesResponse>, t: Throwable) {
                    Log.d("searchF", t.toString())
                }

                override fun onResponse(call: Call<SearchesResponse>, response: Response<SearchesResponse>) {
                    Log.d("search", response.body().toString())
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            searchResponse.postValue(data)
                        }
                    }
                }
            })

        return searchResponse
    }

    fun getMovie(id: Int): LiveData<ApiResponse<MovieResponse>> {
        val movieResponse = MutableLiveData<ApiResponse<MovieResponse>>()
        val networkServices = retrofitServices.createMovieService()

        networkServices
            .getMovie(id)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("getMovie", t.toString())
                }

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            movieResponse.postValue(
                                ApiResponse<MovieResponse>()
                                    .success(data)
                            )
                        }
                    }
                }
            })

        return movieResponse
    }

    fun getMovies(header: String, page: String): LiveData<ApiResponse<MoviesResponse>> {
        val moviesResponse = MutableLiveData<ApiResponse<MoviesResponse>>()
        val networkServices = retrofitServices.createMovieService()

        var path = ""
        when (header) {
            "Now Playing" -> {
                path = "now_playing"
            }
            "Up Coming" -> {
                path = "upcoming"
            }
            "Popular" -> {
                path = "popular"
            }
            "Top Rated" -> {
                path = "top_rated"
            }
        }

        networkServices
            .getMovies(path, page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("getMovies", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = header
                            moviesResponse.postValue(
                                ApiResponse<MoviesResponse>()
                                    .success(data)
                            )
                        }
                    }
                }
            })

        return moviesResponse
    }

    fun getTVShow(id: Int): LiveData<ApiResponse<TVShowResponse>> {
        val tvShowResponse = MutableLiveData<ApiResponse<TVShowResponse>>()

        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVShow(id)
            .enqueue(object : Callback<TVShowResponse> {
                override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                    Log.d("getTVShow", t.toString())
                }

                override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {

                            tvShowResponse.postValue(
                                ApiResponse<TVShowResponse>()
                                    .success(data)
                            )
                        }
                    }
                }

            })

        return tvShowResponse
    }

    fun getTVShows(header: String, page: String): LiveData<ApiResponse<TVShowsResponse>> {
        val tvShowsResponse = MutableLiveData<ApiResponse<TVShowsResponse>>()

        var path = ""
        when (header) {
            "Airing Today" -> {
                path = "airing_today"
            }
            "On The Air" -> {
                path = "on_the_air"
            }
            "Popular" -> {
                path = "popular"
            }
            "Top Rated" -> {
                path = "top_rated"
            }
        }

        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVShows(path, page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("getTVShows", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = header

                            tvShowsResponse.postValue(
                                ApiResponse<TVShowsResponse>()
                                    .success(data)
                            )
                        }
                    }
                }
            })

        return tvShowsResponse
    }
}