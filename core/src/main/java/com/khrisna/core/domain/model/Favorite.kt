package com.khrisna.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favorite(
    var id: Int?,
    var poster: String? = null,
    var dataId: Int? = null,
    var isMovie: Boolean? = null
) : Parcelable