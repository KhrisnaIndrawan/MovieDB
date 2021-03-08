package com.khrisna.filmdb

import android.app.Application
import com.khrisna.core.di.databaseModule
import com.khrisna.core.di.networkModule
import com.khrisna.core.di.repositoryModule
import com.khrisna.filmdb.di.useCaseModule
import com.khrisna.filmdb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}