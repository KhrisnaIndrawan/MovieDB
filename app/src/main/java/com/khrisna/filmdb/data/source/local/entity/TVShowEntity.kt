package com.khrisna.filmdb.data.source.local.entity

data class TVShowEntity(
    var id: String? = null,
    var title: String? = null,
    var poster: String? = null,
    var backdrop: String? = null,
    var overview: String? = null,
    var rating: Float? = null,
    var firstAir: String? = null,
    var genres: MutableList<GenreEntity>? = null
)