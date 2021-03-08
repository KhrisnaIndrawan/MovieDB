package com.khrisna.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khrisna.core.data.source.local.entity.*
import com.khrisna.core.data.source.local.room.converter.Converters


@Database(
    entities = [FavoriteEntity::class, GenreEntity::class, MovieEntity::class,
        MoviesEntity::class, TVShowEntity::class, TVShowsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}