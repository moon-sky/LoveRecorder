package com.moonvsky.testbirthday.service.diary

import com.moonvsky.testbirthday.service.diary.bean.articleinfo.ArticleInfo
import com.moonvsky.testbirthday.service.diary.bean.latestarticle.JSNewArticle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JianShuService {
    @GET("users/slug/1d1dcce04840/public_notes?order_by=shared_at")
    fun getLatestDiary():Call<JSNewArticle>
    @GET("/p/{slug}}")
    fun getDiaryContent(@Path("slug") slug:String):Call<ArticleInfo>
//    @GET("p/1ab09227c891")
//    fun getDiaryContent():Call<ArticleInfo>
    @GET("p/1ab09227c891")
    fun getDiaryContent():Call<String>
    companion object{
        const val BASE_URL="https://www.jianshu.com/asimov/"
    }
}