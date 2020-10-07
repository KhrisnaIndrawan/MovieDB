package com.khrisna.core.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun formatDate(data: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH)
        val date: Date?
        var outputDate = ""
        try {
            date = inputFormat.parse(data)
            outputDate = outputFormat.format(date as Date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return outputDate
    }
}