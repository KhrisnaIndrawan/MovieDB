package com.khrisna.filmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    var header: String,
    @SerializedName("results")
    var movieResponses: MutableList<MovieResponse>
)