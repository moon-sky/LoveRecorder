package com.moonvsky.testbirthday

import com.moonvsky.testbirthday.service.bing.IbingPicService
import com.moonvsky.testbirthday.service.diary.JianShuService
import com.moonvsky.testbirthday.service.diary.bean.articleinfo.ArticleInfo
import com.moonvsky.testbirthday.service.diary.bean.latestarticle.JSNewArticle
import com.moonvsky.testbirthday.service.github.Comments
import com.moonvsky.testbirthday.service.github.IGithubService
import com.moonvsky.testbirthday.service.hotspot.IHotSpotService
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
    fun getLatestArticle():Call<JSNewArticle>{
        return RetrofitHelper.getInstance(JianShuService.BASE_URL).create(JianShuService::class.java).getLatestDiary()
    }
    fun getArticleDetail(slug:String):Call<String>{
        return RetrofitHelper.getInstance(JianShuService.BASE_URL,false).create(JianShuService::class.java).getDiaryContent()
    }
    fun getHotSpots():Call<String>{
        return RetrofitHelper.getInstance(IHotSpotService.BASE_URL,false).create(IHotSpotService::class.java).getHotSpotData()
    }
    fun getGithubComments(): Call<String> {
        return RetrofitHelper.getInstance(IGithubService.BASE_URL,false).create(IGithubService::class.java).getComments()
    }
}