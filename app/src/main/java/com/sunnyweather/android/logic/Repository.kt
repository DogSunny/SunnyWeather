package com.sunnyweather.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {
    fun searchPlaces(city: String) = liveData(Dispatchers.IO) {
        val result: Result<List<Place>> = try {
            val (status, places) = SunnyWeatherNetwork.searchPlaces(city)
            if (status == "ok") {
                Result.success(places)
            }
            else {
                Result.failure(RuntimeException("response status is $status"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}