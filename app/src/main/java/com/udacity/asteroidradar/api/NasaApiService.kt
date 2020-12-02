package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.PictureOfTheDay
import retrofit2.Retrofit
import retrofit2.http.GET

//Retrofit object with the multiple converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(JsonOrRawConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .build()

//Public interface exposing [getAsteroids] and [getImageOfTheDay] method
interface NasaApiService {

    //Returns a list of Asteroids as a String
    @Raw
    @GET("neo/rest/v1/feed?api_key=${Constants.API_KEY}")
    suspend fun getAsteroids(): String

    //Returns the image of the day
    @Json
    @GET("planetary/apod?api_key=${Constants.API_KEY}")
    suspend fun getImageOfTheDay(): PictureOfTheDay
}

//Public Api object exposing the Retrofit service
object NasaApi {
    val retrofitService: NasaApiService by lazy { retrofit.create(NasaApiService::class.java) }
}

//Raw annotation for ScalarsConverterFactory use
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Raw

//Json annotation for MoshiConverterFactory use
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Json