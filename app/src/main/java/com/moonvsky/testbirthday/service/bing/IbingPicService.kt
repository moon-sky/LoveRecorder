package com.moonvsky.testbirthday.service.bing

import retrofit2.Call
import retrofit2.http.GET

interface IbingPicService {
    @GET("?info=true&rand=true")
    fun getRandPic(): Call<String>
    companion object{
        val BASE_URL="https://www.talklee.com/api/bing/"
    }
}