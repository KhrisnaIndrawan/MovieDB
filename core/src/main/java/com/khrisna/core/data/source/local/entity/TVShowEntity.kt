package com.khrisna.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tv_show_entities"
)
data class TVShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tv_show_id")
    var id: Int,

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
    var genres: List<GenreEntity>?
)