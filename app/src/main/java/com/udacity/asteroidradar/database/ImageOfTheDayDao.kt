package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageOfTheDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveImageOfTheDay(image: ImageOfTheDayEntity)

    @Query("SELECT * FROM image_of_the_day WHERE id = 1")
    fun getImageOfTheDay(): LiveData<ImageOfTheDayEntity>
}