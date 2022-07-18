package com.moonvsky.testbirthday.service.poem

import com.moonvsky.testbirthday.service.poem.bean.PoemBean
import retrofit2.Call
import retrofit2.http.GET

interface PoemService {
    @GET("api/channel/one/0/0")
    fun getPoemInfo(): Call<PoemBean>
    companion object{
        val BASE_URL="http://v3.wufazhuce.com:8000/"
    }
}