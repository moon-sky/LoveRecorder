package com.moonvsky.testbirthday

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    fun getDiffTime(curTime:Date):String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        /* 当前系统时间*/
        val date = Date(System.currentTimeMillis())
        val time1: String = simpleDateFormat.format(date)

        /*计算时间差*/

        /*计算时间差*/
        val begin: Date = simpleDateFormat.parse(time1)
//        val end: Date = simpleDateFormat.parse(curTime)
        val diff: Long =  System.currentTimeMillis()-curTime.time
        Log.d("Test","${curTime.time} ${System.currentTimeMillis()} 差值")
        /*计算天数*/
        val days = diff / (1000 * 60 * 60 * 24)
        /*计算小时*/
        val hours = diff % (1000 * 60 * 60 * 24) / (1000 * 60 * 60)
        /*计算分钟*/
        val minutes = diff % (1000 * 60 * 60) / (1000 * 60)
        /*计算秒*/
        val seconds = diff % (1000 * 60) / 1000
        /*  输出
                             System.out.println("保鲜的日期时间差：" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒");*/
        /*  输出
                             System.out.println("保鲜的日期时间差：" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒");*/
        val daojishi = "已经认识 $days 天-$hours 小时-$minutes 分-$seconds 秒"
        return daojishi
    }
}