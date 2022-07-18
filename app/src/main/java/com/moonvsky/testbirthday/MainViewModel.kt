package com.moonvsky.testbirthday

import androidx.lifecycle.ViewModel
import com.moonvsky.testbirthday.service.weather.WeatherBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MainViewModel: ViewModel() {
    suspend fun getWeather(city:String): Call<WeatherBean> {
        return withContext(Dispatchers.IO){ServiceRepository.getWeather(city)}
    }
}