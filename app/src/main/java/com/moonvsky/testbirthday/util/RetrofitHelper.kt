package com.moonvsky.testbirthday.util

import android.content.Context
import android.webkit.WebSettings
import com.moonvsky.testbirthday.MainActivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitHelper {
    fun getInstance(url:String,needJSON:Boolean=true):Retrofit{
        val client=OkHttpClient().newBuilder().addInterceptor { chain ->
            val request = chain.request().newBuilder().removeHeader("User-Agent").addHeader(
                "User-Agent",WebSettings.getDefaultUserAgent(MainActivity.context)
            ).build()
            chain.proceed(request)
        }
        if(needJSON){
            return Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).client(client.build())
                .build()
        }else{
            return Retrofit.Builder().baseUrl(url).addConverterFactory(ScalarsConverterFactory.create()).client(client.build())
                .build()
        }

    }
}