/**
 * Copyright 2022 bejson.com
 */
package com.moonvsky.testbirthday.service

import java.util.*

/**
 * Auto-generated: 2022-07-13 9:47:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
data class Lives(    var province: String? ,
                     var city: String? ,
                     var adcode: String? ,
                     var weather: String? ,
                     var temperature: String? ,
                     var winddirection: String? ,
                     var windpower: String? ,
                     var humidity: String? ,
                     var reporttime: String? ) {
    override fun toString(): String {
        return "$city 今天天气 $weather 温度 $temperature 度 $winddirection 风 $windpower 级 "
    }

}