package com.khrisna.filmdb.data.source.local.entity

data class TVShowsEntity(
    var header: String,
    var tvShow: MutableList<TVShowEntity>
)