package com.khrisna.core.di

import androidx.room.Room
import com.khrisna.core.BuildConfig.BASE_URL
import com.khrisna.core.data.source.FavoriteRepository
import com.khrisna.core.data.source.MovieRepository
import com.khrisna.core.data.source.SearchRepository
import com.khrisna.core.data.source.TVShowRepository
import com.khrisna.core.data.source.local.LocalDataSource
import com.khrisna.core.data.source.local.room.MovieDatabase
import com.khrisna.core.data.source.remote.RemoteDataSource
import com.khrisna.core.data.source.remote.network.service.ApiService
import com.khrisna.core.domain.repository.IFavoriteRepository
import com.khrisna.core.domain.repository.IMovieRepository
import com.khrisna.core.domain.repository.ISearchRepository
import com.khrisna.core.domain.repository.ITVShowRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
    single<ITVShowRepository> { TVShowRepository(get(), get()) }
    single<ISearchRepository> { SearchRepository(get()) }
    single<IFavoriteRepository> { FavoriteRepository(get()) }
}