package com.khrisna.filmdb.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
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
    var genreResponses: MutableList<GenreResponse>
)
