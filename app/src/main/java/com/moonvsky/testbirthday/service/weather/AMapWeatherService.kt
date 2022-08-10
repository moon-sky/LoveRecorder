package com.moonvsky.testbirthday.service.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AMapWeatherService {
    @GET("weatherInfo?key=991105342e15dadca6f1c2c18c3c940c")
    fun getWeatherInfo(@Query("city") name:String): Call<WeatherBean>
    companion object{
        const val BASE_URL="https://restapi.amap.com/v3/weather/"
    }
}