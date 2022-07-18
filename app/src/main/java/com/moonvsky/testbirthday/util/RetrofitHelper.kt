package com.moonvsky.testbirthday.util

import com.moonvsky.testbirthday.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance(url:String,needJSON:Boolean=true):Retrofit{
        if(needJSON){
            return Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }else{
            return Retrofit.Builder().baseUrl(url)
                .build()
        }

    }
}