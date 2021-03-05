package com.khrisna.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_entities")
data class MoviesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movies_id")
    var id: Int?,

    @ColumnInfo(name = "movies_header")
    var header: String? = null,

    @ColumnInfo(name = "movie_list")
    var movies: List<MovieEntity>?
)