package com.moonvsky.testbirthday.service.bing

import android.util.Log

object BingResponseUtils {
    fun parseResponse(response: String): Pair<String,String> {
        val start= response.indexOfFirst { it.toString() == ":" }
        val end= response.indexOfFirst { it.toString() == "," }
        val urlStart= response.indexOf("url:")
        val urlEnd= response.indexOf(",link" )
        Log.d("TEST","response = $response start = $urlStart end = $urlEnd")
        return Pair(response.substring(start,end),response.substring(urlStart,urlEnd).replace("url:",""))
    }
}