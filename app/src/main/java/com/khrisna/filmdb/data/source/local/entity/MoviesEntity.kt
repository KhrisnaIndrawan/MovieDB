package com.khrisna.filmdb.data.source.local.entity

data class MoviesEntity(
    var header: String? = null,
    var movies: MutableList<MovieEntity>? = null
)