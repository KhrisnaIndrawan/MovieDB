package com.khrisna.core.data.source.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.khrisna.core.data.source.local.entity.GenreEntity
import com.khrisna.core.data.source.local.entity.MovieEntity
import com.khrisna.core.data.source.local.entity.TVShowEntity

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromGenres(value: MutableList<GenreEntity>?): String {

            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toGenres(value: String): MutableList<GenreEntity>? {

            val objects = Gson().fromJson(value, Array<GenreEntity>::class.java) as Array<GenreEntity>
            return objects.toMutableList()
        }

        @TypeConverter
        @JvmStatic
        fun fromMovies(value: MutableList<MovieEntity>?): String {

            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toMovies(value: String): MutableList<MovieEntity>? {

            val objects = Gson().fromJson(value, Array<MovieEntity>::class.java) as Array<MovieEntity>
            return objects.toMutableList()
        }

        @TypeConverter
        @JvmStatic
        fun fromTVShows(value: MutableList<TVShowEntity>?): String {

            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toTVShows(value: String): MutableList<TVShowEntity>? {

            val objects = Gson().fromJson(value, Array<TVShowEntity>::class.java) as Array<TVShowEntity>
            return objects.toMutableList()
        }
    }
}