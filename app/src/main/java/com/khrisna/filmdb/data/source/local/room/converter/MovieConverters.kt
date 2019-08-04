package com.khrisna.filmdb.data.source.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.khrisna.filmdb.data.source.local.entity.MovieEntity

class MovieConverters {

    @TypeConverter
    fun listToJson(value: List<MovieEntity>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<MovieEntity>? {

        val objects = Gson().fromJson(value, Array<MovieEntity>::class.java) as Array<MovieEntity>
        return objects.toList()
    }
}