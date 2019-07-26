package com.khrisna.filmdb.data.source.local.entity

data class TVShowEntity(
    var id: String,
    var title: String,
    var poster: String,
    var backdrop: String,
    var overview: String,
    var rating: Float,
    var firstAir: String,
    var genres: MutableList<GenreEntity>
)