package com.khrisna.filmdb.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_entities")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "movies_id")
    var id: Int,

    @ColumnInfo(name = "movies_header")
    var header: String? = null,

    @ColumnInfo(name = "movie_list")
    var movies: MutableList<MovieEntity>? = null
)