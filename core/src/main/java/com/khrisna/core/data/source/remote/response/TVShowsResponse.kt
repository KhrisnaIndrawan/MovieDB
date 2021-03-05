package com.khrisna.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowsResponse(
    @SerializedName("page")
    var page: Int,
    var header: String,
    @SerializedName("results")
    var tvShows: MutableList<TVShowResponse>
)