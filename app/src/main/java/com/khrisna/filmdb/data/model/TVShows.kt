package com.khrisna.filmdb.data.model

import com.google.gson.annotations.SerializedName

data class TVShows(
    var header: String,
    @SerializedName("results")
    var tvShows: MutableList<TVShow>
)