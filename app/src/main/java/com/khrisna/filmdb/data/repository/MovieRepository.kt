package com.khrisna.filmdb.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.model.Movie
import com.khrisna.filmdb.data.model.Movies
import com.khrisna.filmdb.network.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    fun getMovie(id: String): MutableLiveData<Movie> {

        val movie = MutableLiveData<Movie>()

        val networkServices = DataRepository.create()
        networkServices
            .getMovie(id)
            .enqueue(object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        movie.postValue(data)
                    }
                }

            })

        return movie
    }

    fun getNowPlaying(): MutableLiveData<Movies> {

        val movies = MutableLiveData<Movies>()

        val networkServices = DataRepository.create()
        networkServices
            .getMovieNowPlaying()
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Now Playing"
                        movies.postValue(data)
                    }
                }
            })

        return movies
    }

    fun getUpComing(): MutableLiveData<Movies> {

        val movies = MutableLiveData<Movies>()

        val networkServices = DataRepository.create()
        networkServices
            .getMovieUpComing()
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Up Coming"
                        movies.postValue(data)
                    }
                }
            })

        return movies
    }

    fun getPopular(): MutableLiveData<Movies> {

        val movies = MutableLiveData<Movies>()

        val networkServices = DataRepository.create()
        networkServices
            .getMoviePopular()
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Popular"
                        movies.postValue(data)
                    }
                }
            })

        return movies
    }

    fun getTopRated(): MutableLiveData<Movies> {

        val movies = MutableLiveData<Movies>()

        val networkServices = DataRepository.create()
        networkServices
            .getMovieTopRated()
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
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