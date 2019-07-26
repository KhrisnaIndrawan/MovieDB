package com.khrisna.filmdb.data.source.local.entity

data class MoviesEntity(
    var header: String,
    var movies: MutableList<MovieEntity>
)