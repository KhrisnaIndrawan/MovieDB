package com.khrisna.filmdb.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.data.source.remote.response.MoviesEntity
import com.khrisna.filmdb.viewmodel.MoviesViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesResponseViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Rule
    lateinit var rule: InstantTaskExecutorRule

    @Before
    fun setUp() {
        rule = InstantTaskExecutorRule()
        viewModel = MoviesViewModel()
    }

    @Test
    fun getNowPlaying() {
        val movies = MutableLiveData<MoviesEntity>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getMovieUpComing()
            .execute()

        println(response.body()?.movieResponses)

        assertNotNull(movies)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.movieResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.movieResponses?.size)
        }
    }

    @Test
    fun getUpComing() {
        val movies = MutableLiveData<MoviesEntity>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getMovieUpComing()
            .execute()

        println(response.body()?.movieResponses)

        assertNotNull(movies)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.movieResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.movieResponses?.size)
        }
    }

    @Test
    fun getPopular() {
        val movies = MutableLiveData<MoviesEntity>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getMoviePopular()
            .execute()

        println(response.body()?.movieResponses)

        assertNotNull(movies)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.movieResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.movieResponses?.size)
        }
    }

    @Test
    fun getTopRated() {
        val movies = MutableLiveData<MoviesEntity>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getMovieTopRated()
            .execute()

        println(response.body()?.movieResponses)

        assertNotNull(movies)
        if (response.isSuccessful) {
            assertEquals(20, response.body()?.movieResponses?.size)
        } else {
            assertNotEquals(20, response.body()?.movieResponses?.size)
        }
    }
}