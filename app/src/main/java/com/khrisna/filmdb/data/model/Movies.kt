package com.khrisna.filmdb.data.model

import com.google.gson.annotations.SerializedName

data class Movies(
    var header: String,
    @SerializedName("results")
    var movies: MutableList<Movie>
)