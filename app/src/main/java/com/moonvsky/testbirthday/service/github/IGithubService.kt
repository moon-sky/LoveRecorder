package com.moonvsky.testbirthday.service.github

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IGithubService {
    @GET("LoveRecorder/issues/comments")
    fun getComments():Call<String>
    @GET( "/{id}")
    fun getCommentsDetail(@Path("id") id:String):Call<CommentDetail>
    companion object{
        val BASE_URL="https://api.github.com/repos/moon-sky/"
    }
}