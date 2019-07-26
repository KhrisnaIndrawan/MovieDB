package com.khrisna.filmdb.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
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
        fun onResponse(movieResponse: MovieResponse?)
        fun onFailure()
    }

    interface LoadMoviesCallback {
        fun onResponse(movieResponse: MoviesResponse?)
        fun onFailure()
    }

    interface LoadTVShowCallback {
        fun onResponse(tvShowResponse: TVShowResponse?)
        fun onFailure()
    }

    interface LoadTVShowsCallback {
        fun onResponse(tvShowsResponse: TVShowsResponse?)
        fun onFailure()
    }

    fun getMovie(id: String, callback: LoadMovieCallback) {
        val networkServices = retrofitServices.create()
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

    fun getMoviesNowPlaying(callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.create()
        networkServices
            .getMovieNowPlaying()
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

    fun getMoviesUpComing(callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.create()
        networkServices
            .getMovieUpComing()
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

    fun getMoviesPopular(callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.create()
        networkServices
            .getMoviePopular()
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

    fun getMoviesTopRated(callback: LoadMoviesCallback) {
        val networkServices = retrofitServices.create()
        networkServices
            .getMovieTopRated()
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
        val networkServices = retrofitServices.create()
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

    fun getTVShowsAiringToday(callback: LoadTVShowsCallback) {
        val networkServices = retrofitServices.create()
        networkServices
            .getTVAiringToday()
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

    fun getTVShowsOnTheAir(callback: LoadTVShowsCallback) {
        val networkServices = retrofitServices.create()
        networkServices
            .getTVOnTheAir()
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

    fun getTVShowsPopular(callback: LoadTVShowsCallback){
        val networkServices = retrofitServices.create()
        networkServices
            .getTVPopular()
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

    fun getTVShowsTopRated(callback: LoadTVShowsCallback) {
        val networkServices = retrofitServices.create()
        networkServices
            .getTVTopRated()
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