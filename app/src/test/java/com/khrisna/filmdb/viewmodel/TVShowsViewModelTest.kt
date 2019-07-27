package com.khrisna.filmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TVShowsViewModelTest {

    private lateinit var viewModel: TVShowsViewModel
    private var movieRepository = Mockito.mock(MovieRepository::class.java)

    private lateinit var tvShowEntity: TVShowEntity
    private lateinit var tvShowsEntity: TVShowsEntity

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        tvShowEntity = TVShowEntity(
            id = "71446",
            title = "Money Heist",
            overview = "To carry out the biggest heist in history, a mysterious man called The Professor " +
                    "recruits a band of eight robbers who have a single characteristic: none of them has " +
                    "anything to lose.",
            poster = "/MoEKaPFHABtA1xKoOteirGaHl1.jpg",
            backdrop = "/piuRhGiQBYWgW668eSNJ2ug5uAO.jpg",
            rating = 8.1f,
            genres = mutableListOf(),
            firstAir = "2017-05-02"
        )

        tvShowsEntity = TVShowsEntity(
            header = "Header",
            tvShow = mutableListOf()
        )

        for (i in 1..20) {
            tvShowsEntity.tvShow?.add(tvShowEntity)
        }

        viewModel = TVShowsViewModel(movieRepository)
    }

    @Test
    fun getAiringToday() {
        val expectedResult = MutableLiveData<TVShowsEntity>()
        expectedResult.setValue(tvShowsEntity)

        Mockito.`when`(movieRepository.getTVShowsAiringToday())
            .thenReturn(expectedResult)

        val observer: Observer<TVShowsEntity> = Mockito.mock(Observer::class.java) as Observer<TVShowsEntity>

        viewModel.getAiringToday()
        viewModel.airingToday?.observeForever(observer)

        Mockito.verify(movieRepository).getTVShowsAiringToday()

        assertNotNull(viewModel.airingToday)
        assertEquals(20, viewModel.airingToday?.value?.tvShow?.size)
    }

    @Test
    fun getUpComing() {
        val expectedResult = MutableLiveData<TVShowsEntity>()
        expectedResult.setValue(tvShowsEntity)

        Mockito.`when`(movieRepository.getTVShowsOnTheAir())
            .thenReturn(expectedResult)

        val observer: Observer<TVShowsEntity> = Mockito.mock(Observer::class.java) as Observer<TVShowsEntity>

        viewModel.getOnTheAir()
        viewModel.onTheAir?.observeForever(observer)

        Mockito.verify(movieRepository).getTVShowsOnTheAir()

        assertNotNull(viewModel.onTheAir)
        assertEquals(20, viewModel.onTheAir?.value?.tvShow?.size)
    }

    @Test
    fun getPopular() {
        val expectedResult = MutableLiveData<TVShowsEntity>()
        expectedResult.setValue(tvShowsEntity)

        Mockito.`when`(movieRepository.getTVShowsPopular())
            .thenReturn(expectedResult)

        val observer: Observer<TVShowsEntity> = Mockito.mock(Observer::class.java) as Observer<TVShowsEntity>

        viewModel.getPopular()
        viewModel.popular?.observeForever(observer)

        Mockito.verify(movieRepository).getTVShowsPopular()

        assertNotNull(viewModel.popular)
        assertEquals(20, viewModel.popular?.value?.tvShow?.size)
    }

    @Test
    fun getTopRated() {
        val expectedResult = MutableLiveData<TVShowsEntity>()
        expectedResult.setValue(tvShowsEntity)

        Mockito.`when`(movieRepository.getTVShowsTopRated())
            .thenReturn(expectedResult)

        val observer: Observer<TVShowsEntity> = Mockito.mock(Observer::class.java) as Observer<TVShowsEntity>

        viewModel.getTopRated()
        viewModel.topRated?.observeForever(observer)

        Mockito.verify(movieRepository).getTVShowsTopRated()

        assertNotNull(viewModel.topRated)
        assertEquals(20, viewModel.topRated?.value?.tvShow?.size)
    }
}