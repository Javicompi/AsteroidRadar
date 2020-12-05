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

fun getTodayLong(): Long {
    val calendar = resetToDay()
    return calendar.timeInMillis
}

fun getNextWeekLong(): Long {
    val calendar = resetToDay()
    calendar.add(Calendar.DAY_OF_MONTH, 7)
    return calendar.timeInMillis
}

fun resetToDay(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MILLISECOND, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    return calendar
}

