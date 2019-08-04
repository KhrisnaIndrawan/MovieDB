package com.khrisna.filmdb.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "genre_entities"
)
data class GenreEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "genre_id")
    var id: Int,

    @ColumnInfo(name = "genre_name")
    var name: String? = null
)