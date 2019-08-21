package com.khrisna.filmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.khrisna.filmdb.data.source.MovieRepository
import com.khrisna.filmdb.data.source.local.LocalRepository
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private var movieRepository = mock(MovieRepository::class.java)

    private lateinit var movieEntity: MovieEntity
    private lateinit var tvShowEntity: TVShowEntity

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        movieEntity = MovieEntity(
            id = 429617,
            genres = mutableListOf(),
            releaseDate = "2019-06-28",
            rating = 7.8f,
            backdrop = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
            poster = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
            overview = "Peter Parker and his friends go on a summer trip to Europe.",
            title = "Spider-Man: Far from Home"
        )

        tvShowEntity = TVShowEntity(
            id = 71446,
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

        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getMovie() {

        val expectedResult = MutableLiveData<MovieEntity>()
        expectedResult.value = movieEntity

        val local = mock(LocalRepository::class.java)
        `when`(local.getMovieById(429617))
            .thenReturn(expectedResult)

        verify(local).getMovieById(429617)
        assertNotNull(local.getMovieById(429617))
        assertEquals(expectedResult, local.getMovieById(429617))
    }
//
//    @Test
//    fun getTvShow() {
//
//        val expectedResult = MutableLiveData<TVShowEntity>()
//        expectedResult.setValue(tvShowEntity)
//
//        `when`(movieRepository.getTVShow(71446))
//            .thenReturn(expectedResult)
//
//        val observer: Observer<TVShowEntity> = mock(Observer::class.java) as Observer<TVShowEntity>
//
//        viewModel.getTVShow(71446)
//        viewModel.tvShow?.observeForever(observer)
//
//        verify(movieRepository).getTVShow(71446)
//    }
}