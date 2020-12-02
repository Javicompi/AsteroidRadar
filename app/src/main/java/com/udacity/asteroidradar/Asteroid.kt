package com.udacity.asteroidradar

import android.os.Parcelable
import com.udacity.asteroidradar.database.AsteroidEntity
import com.udacity.asteroidradar.utils.stringToDate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
        val id: Long,
        val codename: String,
        val closeApproachDate: String,
        val absoluteMagnitude: Double,
        val estimatedDiameter: Double,
        val relativeVelocity: Double,
        val distanceFromEarth: Double,
        val isPotentiallyHazardous: Boolean
) : Parcelable /*{

    fun toEntity(): AsteroidEntity {
        return AsteroidEntity(
                id,
                codename,
                closeApproachDate,
                absoluteMagnitude,
                estimatedDiameter,
                relativeVelocity,
                distanceFromEarth,
                isPotentiallyHazardous
        )
    }
}*/

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