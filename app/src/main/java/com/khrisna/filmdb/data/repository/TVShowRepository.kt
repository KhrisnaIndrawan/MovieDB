package com.khrisna.filmdb.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.model.TVShow
import com.khrisna.filmdb.data.model.TVShows
import com.khrisna.filmdb.network.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRepository {

    fun getTVShow(id: String): MutableLiveData<TVShow> {

        val tvShow = MutableLiveData<TVShow>()

        val networkServices = DataRepository.create()
        networkServices
            .getTVShow(id)
            .enqueue(object : Callback<TVShow> {
                override fun onFailure(call: Call<TVShow>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShow>, response: Response<TVShow>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        tvShow.postValue(data)
                    }
                }

            })

        return tvShow
    }

    fun getAiringToday(): MutableLiveData<TVShows> {

        val tvShows = MutableLiveData<TVShows>()

        val networkServices = DataRepository.create()
        networkServices
            .getTVAiringToday()
            .enqueue(object : Callback<TVShows> {
                override fun onFailure(call: Call<TVShows>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShows>, response: Response<TVShows>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Airing Today"
                        tvShows.postValue(data)
                    }
                }
            })

        return tvShows
    }

    fun getOnTheAir(): MutableLiveData<TVShows> {

        val tvShows = MutableLiveData<TVShows>()

        val networkServices = DataRepository.create()
        networkServices
            .getTVOnTheAir()
            .enqueue(object : Callback<TVShows> {
                override fun onFailure(call: Call<TVShows>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShows>, response: Response<TVShows>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "On The Air"
                        tvShows.postValue(data)
                    }
                }
            })

        return tvShows
    }

    fun getPopular(): MutableLiveData<TVShows> {

        val tvShows = MutableLiveData<TVShows>()

        val networkServices = DataRepository.create()
        networkServices
            .getTVPopular()
            .enqueue(object : Callback<TVShows> {
                override fun onFailure(call: Call<TVShows>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShows>, response: Response<TVShows>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.header = "Popular"
                        tvShows.postValue(data)
                    }
                }
            })

        return tvShows
    }

    fun getTopRated(): MutableLiveData<TVShows> {

        val tvShows = MutableLiveData<TVShows>()

        val networkServices = DataRepository.create()
        networkServices
            .getTVTopRated()
            .enqueue(object : Callback<TVShows> {
                override fun onFailure(call: Call<TVShows>, t: Throwable) {
                    Log.d("OnFailure", t.toString())
                }

                override fun onResponse(call: Call<TVShows>, response: Response<TVShows>) {
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