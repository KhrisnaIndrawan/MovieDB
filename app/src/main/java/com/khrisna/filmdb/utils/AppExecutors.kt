package com.khrisna.filmdb.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors() {

    private val THREAD_COUNT = 3

    private val diskIO: Executor = DiskIOThreadExecutor()
    private val networkIO: Executor = Executors.newFixedThreadPool(THREAD_COUNT)
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