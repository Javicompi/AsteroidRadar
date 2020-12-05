package com.udacity.asteroidradar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.*
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfTheDay
import com.udacity.asteroidradar.models.toEntity
import com.udacity.asteroidradar.models.toEntityList
import com.udacity.asteroidradar.utils.getNextWeekLong
import com.udacity.asteroidradar.utils.getTodayLong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Repository(private val database: AppDatabase) {

    private val TAG ="Repository"

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

    fun getAllAsteroids(): LiveData<List<Asteroid>> {
        Log.d(TAG, "getAllAsteroids")
        return Transformations.map(database.asteroidDao().getAllAsteroids()) {
            Log.d(TAG, "getAllAsteroids: ${it.size}")
            it?.toModelList()
        }
    }

    fun getTodayAsteroids(): LiveData<List<Asteroid>> {
        Log.d(TAG, "getTodayAsteroids")
        return Transformations.map(database.asteroidDao().getTodayAsteroids(getTodayLong())) {
            Log.d(TAG, "todayLong: ${getTodayLong()}")
            Log.d(TAG, "getTodayAsteroids: ${it.size}")
            it?.toModelList()
        }
    }

    fun getWeekAsteroids(): LiveData<List<Asteroid>> {
        Log.d(TAG, "getWeekAsteroids")
        return Transformations.map(
            database.asteroidDao().getNextWeekAsteroids(getTodayLong(), getNextWeekLong())) {
            Log.d(TAG, "getWeekAsteroids: ${it.size}")
            it?.toModelList()
        }
    }

    fun getImageOfTheDay(): LiveData<PictureOfTheDay> {
        return Transformations.map(database.imageOfTheDayDao().getImageOfTheDay()) {
            Log.d(TAG, "Title: ${it.title}")
            it?.toModel()
        }
    }
}