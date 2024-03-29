package com.khrisna.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchesResponse(
    @SerializedName("results")
    var searches: MutableList<SearchResponse>
)