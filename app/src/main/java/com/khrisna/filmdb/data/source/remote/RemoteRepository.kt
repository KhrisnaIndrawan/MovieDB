package com.khrisna.filmdb.data.source.remote

import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.data.source.remote.response.MovieResponse
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository(private val retrofitServices: RetrofitServices) {

    interface LoadMovieCallback {
        fun onResponse(response: MovieResponse?)
        fun onFailure()
    }

    interface LoadMoviesCallback {
        fun onResponse(response: MoviesResponse?)
        fun onFailure()
    }

    interface LoadTVShowCallback {
        fun onResponse(response: TVShowResponse?)
        fun onFailure()
    }

    interface LoadTVShowsCallback {
        fun onResponse(response: TVShowsResponse?)
        fun onFailure()
    }

    fun getMovie(id: String, callback: LoadMovieCallback) {
        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMovie(id)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getMoviesNowPlaying(page: String, callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMovieNowPlaying(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Now Playing"
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getMoviesUpComing(page: String, callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMovieUpComing(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Up Coming"
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getMoviesPopular(page: String, callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMoviePopular(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Popular"
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getMoviesTopRated(page: String, callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.createMovieService()
        networkServices
            .getMovieTopRated(page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Top Rated"
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getTVShow(id: String, callback: LoadTVShowCallback) {
        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVShow(id)
            .enqueue(object : Callback<TVShowResponse> {
                override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        callback.onResponse(data)
                    }
                }

            })
    }

    fun getTVShowsAiringToday(page: String, callback: LoadTVShowsCallback) {
        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVAiringToday(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Airing Today"
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getTVShowsOnTheAir(page: String, callback: LoadTVShowsCallback) {
        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVOnTheAir(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "On The Air"
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getTVShowsPopular(page: String, callback: LoadTVShowsCallback) {
        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVPopular(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Popular"
                        callback.onResponse(data)
                    }
                }
            })
    }

    fun getTVShowsTopRated(page: String, callback: LoadTVShowsCallback) {
        val networkServices = retrofitServices.createTVShowService()
        networkServices
            .getTVTopRated(page)
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Top Rated"
                        callback.onResponse(data)
                    }
                }
            })
    }
}