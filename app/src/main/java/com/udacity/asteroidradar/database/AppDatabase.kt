package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ImageOfTheDayEntity::class, AsteroidEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun imageOfTheDayDao(): ImageOfTheDayDao

    abstract fun asteroidDao(): AsteroidDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "database").build()
        }
    }
    return INSTANCE
}