package com.udacity.asteroidradar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AppDatabase
import com.udacity.asteroidradar.database.toModel
import com.udacity.asteroidradar.database.toModelList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Repository(private val database: AppDatabase) {

    private val TAG ="Repository"

    val imageOfDay: LiveData<PictureOfTheDay> =
            Transformations.map(database.imageOfTheDayDao().getImageOfTheDay()) {
                it?.toModel()
            }

    val asteroids: LiveData<List<Asteroid>> =
            Transformations.map(database.asteroidDao().getAllAsteroids()) {
                it?.toModelList()
            }

    suspend fun updateImageOfTheDay() {
        withContext(Dispatchers.IO) {
            val imageOfTheDay = NasaApi.retrofitService.getImageOfTheDay()
            Log.d(TAG, "updateImageOfTheDay")
            if (imageOfTheDay.mediaType.equals("image")) {
                Log.d(TAG, "image: ${imageOfTheDay.title}")
                database.imageOfTheDayDao().saveImageOfTheDay(imageOfTheDay.toEntity())
            }
        }
    }

    suspend fun updateAsteroids() {
        withContext(Dispatchers.IO) {
            Log.d(TAG, "updateAsteroids")
            val asteroidsString = NasaApi.retrofitService.getAsteroids()
            if (!asteroidsString.isEmpty()) {
                val asteroids = parseAsteroidsJsonResult(JSONObject(asteroidsString))
                Log.d(TAG, "Asteroids: ${asteroids.size}")
                database.asteroidDao().insertAsteroids(asteroids.toEntityList())
            }
        }
    }
}