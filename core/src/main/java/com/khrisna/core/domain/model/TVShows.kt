package com.khrisna.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShows(
    var id: Int?,
    var header: String? = null,
    var tvShows: List<TVShow>? = null
) : Parcelable