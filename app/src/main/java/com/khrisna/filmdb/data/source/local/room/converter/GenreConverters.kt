package com.khrisna.filmdb.data.source.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.khrisna.filmdb.data.source.local.entity.GenreEntity

class GenreConverters {

    @TypeConverter
    fun listToJson(value: List<GenreEntity>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<GenreEntity>? {

        val objects = Gson().fromJson(value, Array<GenreEntity>::class.java) as Array<GenreEntity>
        return objects.toList()
    }
}