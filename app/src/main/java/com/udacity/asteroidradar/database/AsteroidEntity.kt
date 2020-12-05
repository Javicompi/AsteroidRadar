package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.models.Asteroid
import java.util.*

@Entity(tableName = "asteroids")
data class AsteroidEntity(
        @PrimaryKey
        val id: Long,
        val codename: String,
        val closeApproachDate: Date,
        val absoluteMagnitude: Double,
        val estimatedDiameter: Double,
        val relativeVelocity: Double,
        val distanceFromEarth: Double,
        val isPotentiallyHazardous: Boolean
)

fun List<AsteroidEntity>.toModelList(): List<Asteroid> {
    return map {
        Asteroid(
                id = it.id,
                codename = it.codename,
                //closeApproachDate = dateToString(it.closeApproachDate),
                closeApproachDate = it.closeApproachDate,
                absoluteMagnitude = it.absoluteMagnitude,
                estimatedDiameter = it.estimatedDiameter,
                relativeVelocity = it.relativeVelocity,
                distanceFromEarth = it.distanceFromEarth,
                isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}