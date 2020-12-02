package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.PictureOfTheDay

@Entity(tableName = "image_of_the_day")
data class ImageOfTheDayEntity(
        @PrimaryKey
        val id: Int = 1,
        @ColumnInfo(name = "media_type")
        val mediaType: String,
        val title: String,
        val url: String,
        val hdurl: String,
        val date: String,
        val copyright: String,
        val explanation: String
)

fun ImageOfTheDayEntity.toModel(): PictureOfTheDay {
    return PictureOfTheDay(
            mediaType,
            title,
            url,
            hdurl,
            date,
            copyright,
            explanation
    )
}