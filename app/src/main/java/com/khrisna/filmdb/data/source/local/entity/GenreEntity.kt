package com.khrisna.filmdb.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "genre_entities"
)
data class GenreEntity(
    @ColumnInfo(name = "genre_id")
    var id: String? = null,

    @ColumnInfo(name = "genre_name")
    var name: String? = null
)