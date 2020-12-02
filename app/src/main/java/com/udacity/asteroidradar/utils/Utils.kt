package com.udacity.asteroidradar.utils

import java.text.SimpleDateFormat
import java.util.*

fun stringToDate(value: String): Date {
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.parse(value)
}

fun dateToString(value: Date): String {
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(value)
}

