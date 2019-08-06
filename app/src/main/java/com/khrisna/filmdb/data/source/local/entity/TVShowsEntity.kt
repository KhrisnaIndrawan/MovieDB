package com.khrisna.filmdb.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows_entities")
data class TVShowsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tv_shows_id")
    var id: Int,

    @ColumnInfo(name = "tv_shows_header")
    var header: String? = null,

    @ColumnInfo(name = "tv_show_list")
    var tvShows: MutableList<TVShowEntity>? = null
)