package com.khrisna.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_entities"
)
data class FavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "favorite_id")
    var id: Int?,

    @ColumnInfo(name = "favorite_poster")
    var poster: String? = null,

    @ColumnInfo(name = "favorite_data_id")
    var dataId: Int? = null,

    @ColumnInfo(name = "favorite_is_movie")
    var isMovie: Boolean? = null
)