package com.udacity.asteroidradar.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.Repository
import com.udacity.asteroidradar.database.getDatabase
import retrofit2.HttpException

class RefreshDataWorker(context: Context, params: WorkerParameters) :
        CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        Log.d(WORK_NAME, "do Work")
        val database = getDatabase(applicationContext)
        val repository = Repository(database)
        return try {
            Log.d(WORK_NAME, "updateImageOfTheDay")
            repository.updateImageOfTheDay()
            Log.d(WORK_NAME, "updateAsteroids")
            repository.updateAsteroids()
            Log.d(WORK_NAME, "success")
            Result.success()
        } catch (e: HttpException) {
            Log.d(WORK_NAME, e.message())
            Result.retry()
        }
    }
}