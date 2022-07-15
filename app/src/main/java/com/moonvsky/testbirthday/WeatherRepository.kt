package com.moonvsky.testbirthday

import com.moonvsky.testbirthday.service.AMapWeatherService
import com.moonvsky.testbirthday.service.WeatherBean
import com.moonvsky.testbirthday.util.RetrofitHelper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepository {
    suspend fun getWeather(name:String): Call<WeatherBean> {

        return RetrofitHelper.getInstance().create(AMapWeatherService::class.java).getWeatherInfo(name)
    }
}