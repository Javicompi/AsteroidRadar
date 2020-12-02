package com.udacity.asteroidradar

import androidx.annotation.Nullable
import com.squareup.moshi.Json
import com.udacity.asteroidradar.database.ImageOfTheDayEntity

data class PictureOfTheDay(
        @Json(name = "media_type")
        val mediaType: String,
        val title: String,
        val url: String,
        val hdurl: String,
        val date: String,
        val copyright: String? = "",
        val explanation: String
        ) /*{

    fun toEntity(): ImageOfTheDayEntity {
        return ImageOfTheDayEntity(
                1,
                mediaType,
                title,
                url,
                hdurl,
                date,
                copyright ?: "",
                explanation
        )
    }
}*/
fun PictureOfTheDay.toEntity(): ImageOfTheDayEntity {
    return ImageOfTheDayEntity(
            1,
            mediaType,
            title,
            url,
            hdurl,
            date,
            copyright ?: "",
            explanation
    )
}