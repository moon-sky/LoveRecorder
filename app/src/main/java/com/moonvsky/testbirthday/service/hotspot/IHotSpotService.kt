package com.moonvsky.testbirthday.service.hotspot

import retrofit2.Call
import retrofit2.http.GET

interface IHotSpotService {
    @GET("hotwords")
    fun getHotSpotData():Call<String>
    companion object{
        val BASE_URL="https://api.xhboke.com/"
    }
}