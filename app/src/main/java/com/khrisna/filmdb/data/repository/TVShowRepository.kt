package com.khrisna.filmdb.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.data.source.remote.response.TVShowResponse
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRepository {

    private val retrofitServices = RetrofitServices

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

    fun getAiringToday(): MutableLiveData<TVShowsResponse> {

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

    fun getOnTheAir(): MutableLiveData<TVShowsResponse> {

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

    fun getPopular(): MutableLiveData<TVShowsResponse> {

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

    fun getTopRated(): MutableLiveData<TVShowsResponse> {

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