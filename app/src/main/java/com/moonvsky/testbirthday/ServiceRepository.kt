package com.moonvsky.testbirthday

import com.moonvsky.testbirthday.service.bing.IbingPicService
import com.moonvsky.testbirthday.service.poem.PoemService
import com.moonvsky.testbirthday.service.poem.bean.PoemBean
import com.moonvsky.testbirthday.service.weather.AMapWeatherService
import com.moonvsky.testbirthday.service.weather.WeatherBean
import com.moonvsky.testbirthday.util.RetrofitHelper
import retrofit2.Call

object ServiceRepository {
    fun getWeather(name:String): Call<WeatherBean> {
        return RetrofitHelper.getInstance(AMapWeatherService.BASE_URL).create(AMapWeatherService::class.java).getWeatherInfo(name)
    }
    fun getPoem():Call<PoemBean>{
        return RetrofitHelper.getInstance(PoemService.BASE_URL).create(PoemService::class.java).getPoemInfo()
    }
    fun getBingPic():Call<String>{
        return RetrofitHelper.getInstance(IbingPicService.BASE_URL,false).create(IbingPicService::class.java).getRandPic()
    }
}