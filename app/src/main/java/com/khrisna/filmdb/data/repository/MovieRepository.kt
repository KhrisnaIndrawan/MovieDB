package com.khrisna.filmdb.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.data.source.remote.response.MovieResponse
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val retrofitServices = RetrofitServices

    fun getMovie(id: String): MutableLiveData<MovieResponse> {

        val movie = MutableLiveData<MovieResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getMovie(id)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        movie.postValue(data)
                    }
                }

            })

        return movie
    }

    fun getNowPlaying(): MutableLiveData<MoviesResponse> {

        val movies = MutableLiveData<MoviesResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getMovieNowPlaying()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Now Playing"
                        movies.postValue(data)
                    }
                }
            })

        return movies
    }

    fun getUpComing(): MutableLiveData<MoviesResponse> {

        val movies = MutableLiveData<MoviesResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getMovieUpComing()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Up Coming"
                        movies.postValue(data)
                    }
                }
            })

        return movies
    }

    fun getPopular(): MutableLiveData<MoviesResponse> {

        val movies = MutableLiveData<MoviesResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getMoviePopular()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Popular"
                        movies.postValue(data)
                    }
                }
            })

        return movies
    }

    fun getTopRated(): MutableLiveData<MoviesResponse> {

        val movies = MutableLiveData<MoviesResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getMovieTopRated()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Top Rated"
                        movies.postValue(data)
                    }
                }
            })

        return movies
    }
}