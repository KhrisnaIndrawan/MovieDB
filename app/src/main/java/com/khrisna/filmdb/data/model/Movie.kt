package com.khrisna.filmdb.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    var id: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("backdrop_path")
    var backdrop: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("vote_average")
    var rating: Float,
    @SerializedName("genres")
    var genres: MutableList<Genre>
)
