package com.khrisna.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int?,
    var title: String? = null,
    var overview: String? = null,
    var poster: String? = null,
    var backdrop: String? = null,
    var releaseDate: String? = null,
    var rating: Float? = null,
    var genres: List<Genre>? = null
) : Parcelable
