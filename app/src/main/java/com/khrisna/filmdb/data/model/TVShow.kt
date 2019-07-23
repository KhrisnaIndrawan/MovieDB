package com.khrisna.filmdb.data.model

import com.google.gson.annotations.SerializedName

data class TVShow(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var title: String,
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("backdrop_path")
    var backdrop: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("vote_average")
    var rating: Float,
    @SerializedName("first_air_date")
    var firstAir: String,
    @SerializedName("genres")
    var genres: MutableList<Genre>
)