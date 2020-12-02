package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfTheDay
import com.udacity.asteroidradar.Repository
import com.udacity.asteroidradar.database.getDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = "MainViewModel"

    private val database = getDatabase(application)
    private val repository = Repository(database)

    val imageOfTheDay = repository.imageOfDay
    val asteroids = repository.asteroids

    val progressBarVisible = Transformations.map(asteroids) {
        it == null
    }

    val recyclerViewVisible = Transformations.map(asteroids) {
        it != null && it.isNotEmpty()
    }

    val noAsteroidsTextviewVisible = Transformations.map(asteroids) {
        it != null && it.isEmpty()
    }

    private val _navigateToDetail = MutableLiveData<Asteroid>()
    val navigateToDetail: LiveData<Asteroid>
        get() = _navigateToDetail

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToDetail.value = asteroid
    }

    fun onAsteroidDetailNavigated() {
        _navigateToDetail.value = null
    }
}