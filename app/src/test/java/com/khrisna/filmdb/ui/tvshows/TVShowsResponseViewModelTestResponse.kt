package com.khrisna.filmdb.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.viewmodel.TVShowsViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TVShowsResponseViewModelTestResponse {

    private lateinit var viewModel: TVShowsViewModel

    @Rule
    lateinit var rule: InstantTaskExecutorRule

    @Before
    fun setUp() {
        rule = InstantTaskExecutorRule()
        viewModel = TVShowsViewModel()
    }

    @Test
    fun getAiringToday() {
        val tvShows = MutableLiveData<MoviesResponse>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getTVAiringToday()
            .execute()

        println(response.body()?.tvShowResponses)

        assertNotNull(tvShows)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.tvShowResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.tvShowResponses?.size)
        }
    }

    @Test
    fun getOnTheAir() {
        val tvShows = MutableLiveData<MoviesResponse>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getTVOnTheAir()
            .execute()

        println(response.body()?.tvShowResponses)

        assertNotNull(tvShows)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.tvShowResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.tvShowResponses?.size)
        }
    }

    @Test
    fun getPopular() {
        val tvShows = MutableLiveData<MoviesResponse>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getTVPopular()
            .execute()

        println(response.body()?.tvShowResponses)

        assertNotNull(tvShows)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.tvShowResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.tvShowResponses?.size)
        }
    }

    @Test
    fun getTopRated() {
        val tvShows = MutableLiveData<MoviesResponse>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getTVTopRated()
            .execute()

        println(response.body()?.tvShowResponses)

        assertNotNull(tvShows)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.tvShowResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.tvShowResponses?.size)
        }
    }
}