package com.khrisna.filmdb.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors {

    private val diskIO: Executor = DiskIOThreadExecutor()
    private val networkIO: Executor = Executors.newFixedThreadPool(3)
    private val mainThread: Executor = MainThreadExecutor()

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }
}