package com.khrisna.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class SearchResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("poster_path")
    var poster: String
)