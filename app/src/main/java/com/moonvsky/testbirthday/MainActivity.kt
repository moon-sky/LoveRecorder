package com.moonvsky.testbirthday

import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.core.view.isVisible
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.moonvsky.testbirthday.databinding.ActivityMainBinding
import com.moonvsky.testbirthday.service.bing.BingResponseUtils
import com.moonvsky.testbirthday.service.poem.bean.Content_list
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.droidsonroids.gif.GifTextView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding: ActivityMainBinding;
    private lateinit var date: Date;
    private lateinit var contentList: List<Content_list>
    private var curPageIndex: Int = 0
    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    date = Date(2022 - 1900, 1, 7)
                    binding.tvTime.text = TimeUtils().getDiffTime(date)
                    sendEmptyMessageDelayed(0, 1000)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.love)
        findViewById<TextView>(R.id.button).setOnClickListener {
            findViewById<GifTextView>(R.id.gif).visibility = View.VISIBLE
            findViewById<GifTextView>(R.id.bg).visibility = View.VISIBLE
            mediaPlayer.start()
        }
        binding.time.setOnClickListener {
            handler.removeMessages(0)
            handler.sendEmptyMessage(0)
        }
        binding.weather.setOnClickListener {
            MainScope().launch {
                val beijing = withContext(Dispatchers.IO) {
                    ServiceRepository.getWeather(Constants.HAIDIAN_CODE).execute().body()
                }
                binding.tvWeather.text = beijing?.toString() ?: "没查到"
                val bijie = withContext(Dispatchers.IO) {
                    ServiceRepository.getWeather(Constants.BIJIE_CODE).execute().body()
                }
                binding.tvWeather2.text = bijie?.toString() ?: "没查到"
            }
        }
        binding.update.setOnClickListener {
            curPageIndex = (curPageIndex + 1) % contentList.size
            MainScope().launch {
               val result= withContext(Dispatchers.IO){
                    ServiceRepository.getBingPic().execute()
                }
                updateBgAndPoem(BingResponseUtils.parseResponse(
                    result.body()!!).second)
                Log.d("TAG","getRandPic result:${result.body()} title:${BingResponseUtils.parseResponse(
                    result.body()!!)} url= ${BingResponseUtils.parseResponse(
                    result.body()!!).second}")
            }

        }
        MainScope().launch {
            val poem = withContext(Dispatchers.IO) {
                ServiceRepository.getPoem().execute().body()
            }
            contentList = poem?.data?.content_list as List<Content_list>
            val size = contentList.size
            curPageIndex = (Math.random() * size).toInt()
            updateBgAndPoem(contentList.get(curPageIndex).img_url)
        }
        binding.ivHide.setOnClickListener {
            if(binding.clContent.isVisible){
                it.background=resources.getDrawable(R.drawable.ic_hide)
                binding.clContent.visibility=View.INVISIBLE
            }else{
                it.background=resources.getDrawable(R.drawable.ic_show)
                binding.clContent.visibility=View.VISIBLE
            }
        }
    }

    private fun updateBgAndPoem(url:String) {
        binding.tvPoem.text = contentList.get(curPageIndex).forward ?: ""
        Glide.with(this@MainActivity).asBitmap().centerCrop().placeholder(R.drawable.icon).load(url)
            .listener(object :
                RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("TEST", e?.message.toString())
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        Palette.from(resource).generate {
                            val vibrantColor =
                                it?.getVibrantColor(resources.getColor(R.color.brown))
//                            val lightVibrantColor =
//                                it?.getLightVibrantColor(resources.getColor(R.color.design_default_color_primary))
                            val darkVibrantColor =
                                it?.getLightVibrantColor(resources.getColor(R.color.brown))
                            val dominantColor =
                                it?.getDominantColor(resources.getColor(R.color.design_default_color_primary))
//                            val mutedColor =
//                                it?.getMutedColor(resources.getColor(R.color.design_default_color_primary))
                            if (vibrantColor != null) {
                                binding.weather.setBackgroundColor(
                                    ColorUtils.compositeColors(
                                        vibrantColor,
                                        resources.getColor(R.color.brown)
                                    )
                                )

                                binding.button.setBackgroundColor(vibrantColor)
                                binding.time.setBackgroundColor(vibrantColor)
                                binding.tvPoem.setBackgroundColor(vibrantColor)
                            }
                        }
                    }
                    return false
                }
            }).into(binding.ivPoem)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacksAndMessages(null)
    }
}