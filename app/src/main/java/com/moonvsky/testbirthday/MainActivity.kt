package com.moonvsky.testbirthday

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.moonvsky.testbirthday.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.droidsonroids.gif.GifTextView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer:MediaPlayer
    private lateinit var binding:ActivityMainBinding;
    private lateinit var date:Date;
    private val handler:Handler= object:Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                0->{
                    date=Date(2022-1900,1,7)
                    binding.tvTime.text=TimeUtils().getDiffTime(date)
                    sendEmptyMessageDelayed(0,1000)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer= MediaPlayer.create(this,R.raw.love)
        findViewById<TextView>(R.id.button).setOnClickListener{
            findViewById<TextView>(R.id.tv_name).text=getString(R.string.welcome_tips)
            findViewById<GifTextView>(R.id.gif).visibility= View.VISIBLE
            findViewById<GifTextView>(R.id.bg).visibility= View.VISIBLE
            mediaPlayer.start()
        }
        binding.time.setOnClickListener {
            handler.removeMessages(0)
            handler.sendEmptyMessage(0)
        }
        binding.weather.setOnClickListener {
            MainScope().launch{
                val beijing= withContext(Dispatchers.IO){
                    WeatherRepository.getWeather("110108").execute().body()
                }
                binding.tvWeather.text= beijing?.toString()?: "没查到"
                val bijie= withContext(Dispatchers.IO){
                    WeatherRepository.getWeather("520525").execute().body()
                }
                binding.tvWeather2.text=bijie?.toString()?:"没查到"
            }
        }



//        val service: AMapWeatherService = retrofit.create(AMapWeatherService::class)
    }
    companion object{
        val BASE_URL="https://restapi.amap.com/v3/weather/"
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacksAndMessages(null)
    }
}