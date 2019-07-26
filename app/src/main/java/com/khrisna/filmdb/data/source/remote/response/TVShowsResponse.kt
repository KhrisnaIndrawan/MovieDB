package com.khrisna.filmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowsResponse(
    var header: String,
    @SerializedName("results")
    var tvShows: MutableList<TVShowResponse>
)