//package com.khrisna.filmdb.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.khrisna.filmdb.data.source.MovieRepository
//import com.khrisna.filmdb.data.source.local.entity.MovieEntity
//import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito
//
//class MoviesViewModelTest {
//
//    private lateinit var viewModel: MoviesViewModel
//    private var movieRepository = Mockito.mock(MovieRepository::class.java)
//
//    private lateinit var movieEntity: MovieEntity
//    private lateinit var moviesEntity: MoviesEntity
//    private val page = "1"
//
//    @Rule
//    @JvmField
//    val rule = InstantTaskExecutorRule()
//
//    @Before
//    fun setUp() {
//        movieEntity = MovieEntity(
//            id = 429617,
//            genres = mutableListOf(),
//            releaseDate = "2019-06-28",
//            rating = 7.8f,
//            backdrop = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
//            poster = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
//            overview = "Peter Parker and his friends go on a summer trip to Europe.",
//            title = "Spider-Man: Far from Home"
//        )
//
//        moviesEntity = MoviesEntity(
//            header = "Header",
//            movies = mutableListOf()
//        )
//
//        for (i in 1..20) {
//            moviesEntity.movies?.add(movieEntity)
//        }
//
//        viewModel = MoviesViewModel(movieRepository)
//    }
//
//    @Test
//    fun getNowPlaying() {
//        val expectedResult = MutableLiveData<MoviesEntity>()
//        expectedResult.setValue(moviesEntity)
//
//        Mockito.`when`(movieRepository.getMoviesNowPlaying(page))
//            .thenReturn(expectedResult)
//
//        val observer: Observer<MoviesEntity> = Mockito.mock(Observer::class.java) as Observer<MoviesEntity>
//
//        viewModel.getNowPlaying()
//        viewModel.nowPlaying?.observeForever(observer)
//
//        Mockito.verify(movieRepository).getMoviesNowPlaying(page)
//
//        assertNotNull(viewModel.nowPlaying)
//        assertEquals(20, viewModel.nowPlaying?.value?.movies?.size)
//    }
//
//    @Test
//    fun getUpComing() {
//        val expectedResult = MutableLiveData<MoviesEntity>()
//        expectedResult.setValue(moviesEntity)
//
//        Mockito.`when`(movieRepository.getMoviesUpComing(page))
//            .thenReturn(expectedResult)
//
//        val observer: Observer<MoviesEntity> = Mockito.mock(Observer::class.java) as Observer<MoviesEntity>
//
//        viewModel.getUpComing()
//        viewModel.upComing?.observeForever(observer)
//
//        Mockito.verify(movieRepository).getMoviesUpComing(page)
//
//        assertNotNull(viewModel.upComing)
//        assertEquals(20, viewModel.upComing?.value?.movies?.size)
//    }
//
//    @Test
//    fun getPopular() {
//        val expectedResult = MutableLiveData<MoviesEntity>()
//        expectedResult.setValue(moviesEntity)
//
//        Mockito.`when`(movieRepository.getMoviesPopular(page))
//            .thenReturn(expectedResult)
//
//        val observer: Observer<MoviesEntity> = Mockito.mock(Observer::class.java) as Observer<MoviesEntity>
//
//        viewModel.getPopular()
//        viewModel.popular?.observeForever(observer)
//
//        Mockito.verify(movieRepository).getMoviesPopular(page)
//
//        assertNotNull(viewModel.popular)
//        assertEquals(20, viewModel.popular?.value?.movies?.size)
//    }
//
//    @Test
//    fun getTopRated() {
//        val expectedResult = MutableLiveData<MoviesEntity>()
//        expectedResult.setValue(moviesEntity)
//
//        Mockito.`when`(movieRepository.getMoviesTopRated(page))
//            .thenReturn(expectedResult)
//
//        val observer: Observer<MoviesEntity> = Mockito.mock(Observer::class.java) as Observer<MoviesEntity>
//
//        viewModel.getTopRated()
//        viewModel.topRated?.observeForever(observer)
//
//        Mockito.verify(movieRepository).getMoviesTopRated(page)
//
//        assertNotNull(viewModel.topRated)
//        assertEquals(20, viewModel.topRated?.value?.movies?.size)
//    }
//}