package com.khrisna.filmdb.data.source.local.entity

data class MovieEntity(
    var id: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var poster: String? = null,
    var backdrop: String? = null,
    var releaseDate: String? = null,
    var rating: Float? = null,
    var genres: MutableList<GenreEntity>? = null
)
