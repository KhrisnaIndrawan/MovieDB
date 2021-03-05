package com.khrisna.core.utils

enum class ReleaseType(val type: String, val text: String) {
    NOW_PLAYING("now_playing", "Now Playing"),
    UP_COMING("upcoming", "Up Coming"),
    POPULAR("popular", "Popular"),
    TOP_RATED("top_rated", "Top Rated"),
    AIRING_TODAY("airing_today", "Airing Today"),
    ON_THE_AIR("on_the_air", "On The Air")
}