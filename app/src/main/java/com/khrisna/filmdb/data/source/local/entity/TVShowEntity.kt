package com.khrisna.filmdb.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "tv_show_entities"
)
data class TVShowEntity(
    @ColumnInfo(name = "tv_show_id")
    var id: String? = null,

    @ColumnInfo(name = "tv_show_title")
    var title: String? = null,

    @ColumnInfo(name = "tv_show_poster")
    var poster: String? = null,

    @ColumnInfo(name = "tv_show_backdrop")
    var backdrop: String? = null,

    @ColumnInfo(name = "tv_show_overview")
    var overview: String? = null,

    @ColumnInfo(name = "tv_show_rating")
    var rating: Float? = null,

    @ColumnInfo(name = "tv_show_first_air")
    var firstAir: String? = null,

    @ColumnInfo(name = "tv_show_genres")
    var genres: MutableList<GenreEntity>? = null
)