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

    fun getMoviesNowPlaying(): MutableLiveData<MoviesResponse> {

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

    fun getMoviesUpComing(): MutableLiveData<MoviesResponse> {

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

    fun getMoviesPopular(): MutableLiveData<MoviesResponse> {

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

    fun getMoviesTopRated(): MutableLiveData<MoviesResponse> {

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

    fun getTVShow(id: String): MutableLiveData<TVShowResponse> {

        val tvShow = MutableLiveData<TVShowResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getTVShow(id)
            .enqueue(object : Callback<TVShowResponse> {
                override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        tvShow.postValue(data)
                    }
                }

            })

        return tvShow
    }

    fun getTVShowsAiringToday(): MutableLiveData<TVShowsResponse> {

        val tvShows = MutableLiveData<TVShowsResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getTVAiringToday()
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Airing Today"
                        tvShows.postValue(data)
                    }
                }
            })

        return tvShows
    }

    fun getTVShowsOnTheAir(): MutableLiveData<TVShowsResponse> {

        val tvShows = MutableLiveData<TVShowsResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getTVOnTheAir()
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "On The Air"
                        tvShows.postValue(data)
                    }
                }
            })

        return tvShows
    }

    fun getTVShowsPopular(): MutableLiveData<TVShowsResponse> {

        val tvShows = MutableLiveData<TVShowsResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getTVPopular()
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Popular"
                        tvShows.postValue(data)
                    }
                }
            })

        return tvShows
    }

    fun getTVShowsTopRated(): MutableLiveData<TVShowsResponse> {

        val tvShows = MutableLiveData<TVShowsResponse>()

        val networkServices = retrofitServices.create()
        networkServices
            .getTVTopRated()
            .enqueue(object : Callback<TVShowsResponse> {
                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShowsResponse>, response: Response<TVShowsResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Top Rated"
                        tvShows.postValue(data)
                    }
                }
            })

        return tvShows
    }
}