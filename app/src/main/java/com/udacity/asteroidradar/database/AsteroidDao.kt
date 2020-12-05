package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAsteroids(asteroids: List<AsteroidEntity>)

    @Query("SELECT * FROM asteroids ORDER BY closeApproachDate ASC")
    //fun getAllAsteroids(): LiveData<List<AsteroidEntity>>
    fun getAllAsteroids(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroids WHERE closeApproachDate = :today")
    fun getTodayAsteroids(today: Long): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroids WHERE closeApproachDate BETWEEN :today AND :nextWeek ORDER BY closeApproachDate ASC")
    fun getNextWeekAsteroids(today: Long, nextWeek: Long): LiveData<List<AsteroidEntity>>

    @Query("DELETE FROM asteroids")
    fun deleteAllAsteroids()
}