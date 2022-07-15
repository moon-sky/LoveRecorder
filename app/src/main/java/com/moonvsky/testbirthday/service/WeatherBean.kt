/**
 * Copyright 2022 bejson.com
 */
package com.moonvsky.testbirthday.service

/**
 * Auto-generated: 2022-07-13 9:47:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
data class WeatherBean(val status: String, var count: String,var infocode: String,var info: String?,var lives: List<Lives>?){
    override fun toString(): String {
        return lives?.get(0).toString()
    }
}