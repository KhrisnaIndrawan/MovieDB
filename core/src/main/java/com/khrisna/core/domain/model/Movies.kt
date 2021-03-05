package com.khrisna.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    var id: Int?,
    var header: String? = null,
    var movies: List<Movie>? = null
) : Parcelable