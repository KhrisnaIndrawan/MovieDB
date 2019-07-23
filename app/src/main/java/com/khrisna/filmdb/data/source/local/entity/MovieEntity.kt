package com.khrisna.filmdb.data.source.local.entity

data class MovieEntity(
    var id: String,
    var title: String,
    var overview: String,
    var poster: String,
    var backdrop: String,
    var releaseDate: String,
    var rating: Float,
    var genreResponses: MutableList<GenreEntity>
)
