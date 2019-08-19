package com.khrisna.filmdb.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.data.source.remote.response.*
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

    fun getMoviesNowPlaying(page: String): LiveData<ApiResponse<MoviesResponse>> {
        val moviesResponse = MutableLiveData<ApiResponse<MoviesResponse>>()
        val networkServices = retrofitServices.createMovieService()

        networkServices
            .getMovieNowPlaying(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("getMoviesNowPlaying", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "Now Playing"
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

    fun getMoviesUpComing(page: String): LiveData<ApiResponse<MoviesResponse>> {
        val moviesResponse = MutableLiveData<ApiResponse<MoviesResponse>>()

        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMovieUpComing(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("getMoviesUpComing", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "Up Coming"

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

    fun getMoviesPopular(page: String): LiveData<ApiResponse<MoviesResponse>> {
        val moviesResponse = MutableLiveData<ApiResponse<MoviesResponse>>()

        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMoviePopular(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("getMoviesPopular", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "Popular"

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

    fun getMoviesTopRated(page: String): LiveData<ApiResponse<MoviesResponse>> {
        val moviesResponse = MutableLiveData<ApiResponse<MoviesResponse>>()

        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMovieTopRated(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("getMoviesTopRated", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "Top Rated"

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

    fun getTVShowsAiringToday(page: String): LiveData<ApiResponse<TVShowsResponse>> {
        val tvShowsResponse = MutableLiveData<ApiResponse<TVShowsResponse>>()

        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVAiringToday(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("getTVShowsAiringToday", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "Airing Today"

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

    fun getTVShowsOnTheAir(page: String): LiveData<ApiResponse<TVShowsResponse>> {
        val tvShowsResponse = MutableLiveData<ApiResponse<TVShowsResponse>>()

        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVOnTheAir(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("getTVShowsAiringToday", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "On The Air"

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

    fun getTVShowsPopular(page: String): LiveData<ApiResponse<TVShowsResponse>> {
        val tvShowsResponse = MutableLiveData<ApiResponse<TVShowsResponse>>()

        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVPopular(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("getTVShowsPopular", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "Popular"

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

    fun getTVShowsTopRated(page: String): LiveData<ApiResponse<TVShowsResponse>> {
        val tvShowsResponse = MutableLiveData<ApiResponse<TVShowsResponse>>()

        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVTopRated(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("getTVShowsTopRated", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            data.header = "Top Rated"

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