package com.khrisna.filmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String
)