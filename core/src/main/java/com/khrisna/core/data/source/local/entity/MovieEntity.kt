package com.khrisna.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie_entities"
)
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var id: Int,

    @ColumnInfo(name = "movie_title")
    var title: String? = null,

    @ColumnInfo(name = "movie_overview")
    var overview: String? = null,

    @ColumnInfo(name = "movie_poster")
    var poster: String? = null,

    @ColumnInfo(name = "movie_backdrop")
    var backdrop: String? = null,

    @ColumnInfo(name = "movie_release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "movie_rating")
    var rating: Float? = null,

    @ColumnInfo(name = "movie_genres")
    var genres: List<GenreEntity>?
)
