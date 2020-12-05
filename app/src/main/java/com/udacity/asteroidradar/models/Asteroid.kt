package com.udacity.asteroidradar.models

import android.os.Parcelable
import com.udacity.asteroidradar.database.AsteroidEntity
import com.udacity.asteroidradar.utils.dateToString
import com.udacity.asteroidradar.utils.stringToDate
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Asteroid(
        val id: Long,
        val codename: String,
        val closeApproachDate: Date,
        val absoluteMagnitude: Double,
        val estimatedDiameter: Double,
        val relativeVelocity: Double,
        val distanceFromEarth: Double,
        val isPotentiallyHazardous: Boolean
) : Parcelable

fun List<Asteroid>.toEntityList(): List<AsteroidEntity> {
    return map {
        AsteroidEntity(
                id = it.id,
                codename = it.codename,
                //closeApproachDate = stringToDate(it.closeApproachDate),
                closeApproachDate = it.closeApproachDate,
                absoluteMagnitude = it.absoluteMagnitude,
                estimatedDiameter = it.estimatedDiameter,
                relativeVelocity = it.relativeVelocity,
                distanceFromEarth = it.distanceFromEarth,
                isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun Asteroid.closeApproachDateToString(): String {
    return dateToString(closeApproachDate)
}