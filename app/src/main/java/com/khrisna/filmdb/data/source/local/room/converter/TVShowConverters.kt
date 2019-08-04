package com.khrisna.filmdb.data.source.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity

class TVShowConverters {

    @TypeConverter
    fun listToJson(value: List<TVShowEntity>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<TVShowEntity>? {

        val objects = Gson().fromJson(value, Array<TVShowEntity>::class.java) as Array<TVShowEntity>
        return objects.toList()
    }
}