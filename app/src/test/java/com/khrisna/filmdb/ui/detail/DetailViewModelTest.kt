package com.khrisna.filmdb.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.remote.network.RetrofitServices
import com.khrisna.filmdb.data.source.remote.response.MovieEntity
import com.khrisna.filmdb.viewmodel.DetailViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @Rule
    lateinit var rule: InstantTaskExecutorRule

    @Before
    fun setUp() {
        rule = InstantTaskExecutorRule()
        viewModel = DetailViewModel()
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getMovie("429617")
            .execute()

        println(response.body())

        assertNotNull(movie)
        if (response.isSuccessful) {
            assertEquals("Spider-Man: Far from Home", response.body()?.title)
        } else {
            assertNotEquals("Spider-Man: Far from Home", response.body()?.title)
        }
    }

    @Test
    fun getTvShow() {
        val movie = MutableLiveData<MovieEntity>()

        val networkServices = RetrofitServices.create()
        val response = networkServices
            .getTVShow("62745")
            .execute()

        println(response.body())

        assertNotNull(movie)
        if (response.isSuccessful) {
            assertEquals("Is It Wrong to Try to Pick Up Girls in a Dungeon?", response.body()?.title)
        } else {
            assertNotEquals("Is It Wrong to Try to Pick Up Girls in a Dungeon?", response.body()?.title)
        }
    }
}