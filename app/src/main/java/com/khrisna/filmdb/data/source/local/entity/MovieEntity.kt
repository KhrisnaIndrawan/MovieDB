package com.khrisna.filmdb.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "movie_entities"
)
data class MovieEntity(
    @ColumnInfo(name = "movie_id")
    var id: String? = null,

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
    var genres: MutableList<GenreEntity>? = null
)
