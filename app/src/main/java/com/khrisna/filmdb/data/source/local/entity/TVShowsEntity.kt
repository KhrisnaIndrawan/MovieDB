package com.khrisna.filmdb.data.source.local.entity

data class TVShowsEntity(
    var header: String? = null,
    var tvShow: MutableList<TVShowEntity>? = null
)