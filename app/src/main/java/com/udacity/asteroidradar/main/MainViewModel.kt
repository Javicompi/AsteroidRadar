package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Repository
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.models.Asteroid

enum class Filter { ALL, TODAY, WEEK }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MainViewModel"

    private val database = getDatabase(application)
    private val repository = Repository(database)

    val selectedFilter = MutableLiveData<Filter>()

    val imageOfTheDay = repository.getImageOfTheDay()

    val asteroids: LiveData<List<Asteroid>> = selectedFilter.switchMap { filter ->
        Log.d(TAG, "Filter ${filter}")
        if ((filter == null) || (filter.equals(Filter.ALL))) {
            repository.getAllAsteroids()
        } else if (filter.equals(Filter.TODAY)) {
            repository.getTodayAsteroids()
        } else {
            repository.getWeekAsteroids()
        }
    }

    val progressBarVisible = Transformations.map(asteroids) {
        it == null
    }

    val recyclerViewVisible = Transformations.map(asteroids) {
        it != null && it.isNotEmpty()
    }

    val noAsteroidsTextviewVisible = Transformations.map(asteroids) {
        it != null && it.isEmpty()
    }

    init {
        selectedFilter.value = Filter.ALL
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