package com.moonvsky.testbirthday

import android.content.Context
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
import com.google.gson.Gson
import com.moonvsky.testbirthday.databinding.ActivityMainBinding
import com.moonvsky.testbirthday.service.bing.BingResponseUtils
import com.moonvsky.testbirthday.service.github.Comments
import com.moonvsky.testbirthday.service.poem.bean.Content_list
import com.moonvsky.testbirthday.view.HotSpotTagsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import pl.droidsonroids.gif.GifTextView
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding: ActivityMainBinding;
    private lateinit var date: Date;
    private lateinit var contentList: List<Content_list>
    private var curPageIndex: Int = 0
    private val TAG = "MainActivity"
    private var imgBmp: Bitmap? = null
    companion object{
       lateinit var context:Context
    }
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
        context=applicationContext
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.love)
        findViewById<TextView>(R.id.gift).setOnClickListener {
            findViewById<GifTextView>(R.id.gif).visibility = View.VISIBLE
            findViewById<GifTextView>(R.id.bg).visibility = View.VISIBLE
            mediaPlayer.start()
        }
        binding.time.setOnClickListener {
            handler.removeMessages(0)
            binding.tvTime.visibility = View.VISIBLE
            handler.sendEmptyMessage(0)
        }
        binding.weather.setOnClickListener {
            MainScope().launch {
                val beijing = withContext(Dispatchers.IO) {
                    ServiceRepository.getWeather(Constants.HAIDIAN_CODE).execute().body()
                }
                Log.d(TAG, "北京：" + beijing)
                if (beijing != null) {
                    binding.tvWeather.visibility = View.VISIBLE
                    binding.tvWeather.text = beijing.toString()
                }
                val bijie = withContext(Dispatchers.IO) {
                    ServiceRepository.getWeather(Constants.BIJIE_CODE).execute().body()
                }
                if (bijie != null) {
                    binding.tvWeather2.visibility = View.VISIBLE
                    binding.tvWeather2.text = bijie.toString()
                }

            }
        }
        binding.update.setOnClickListener {
            curPageIndex = (curPageIndex + 1) % contentList.size
            MainScope().launch {
                val result = withContext(Dispatchers.IO) {
                    ServiceRepository.getBingPic().execute()
                }
                updateBgAndPoem(
                    BingResponseUtils.parseResponse(
                        result.body()!!
                    ).second
                )
                Log.d(
                    "TAG", "getRandPic result:${result.body()} title:${
                        BingResponseUtils.parseResponse(
                            result.body()!!
                        )
                    } url= ${
                        BingResponseUtils.parseResponse(
                            result.body()!!
                        ).second
                    }"
                )
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
            if (binding.clContent.isVisible) {
                it.background = resources.getDrawable(R.drawable.ic_hide)
                binding.clContent.visibility = View.INVISIBLE
            } else {
                it.background = resources.getDrawable(R.drawable.ic_show)
                binding.clContent.visibility = View.VISIBLE
            }
        }


        binding.diary.setOnClickListener {
//            MainScope().launch {
//                val diaryContent = withContext(Dispatchers.IO) {
//                    ServiceRepository.getArticleDetail("slug").execute().body()
//                }
//                Log.d(TAG,"diaryContent = $diaryContent")
//                if (diaryContent != null) {
//                    binding.tvDiary.text=diaryContent
//                }
//            }
            MainScope().launch {
                val comments = withContext(Dispatchers.IO) {
                    ServiceRepository.getGithubComments().execute().run {
                        Log.d(TAG,"message:${this.message()} errorBody:${this.raw()}")
                        this.body()
                    }
                }
                val commentsJSONObject =
                    Gson().fromJson("{\"comments\":$comments}", Comments::class.java)
                Log.d(TAG, "comments:${comments}")
                Log.d(TAG, "commentsJSONObject:${commentsJSONObject}")
                if (commentsJSONObject != null && commentsJSONObject.comments != null) {
                    val size = commentsJSONObject.comments.size
                    if (size > 0) {
                        binding.tvComments.visibility = View.VISIBLE
                        val time = commentsJSONObject.comments[size - 1].created_at
                        val finalTime=time.removeRange(time.indexOf("T"),time.length)
                        val content = commentsJSONObject.comments[size - 1].body
                        binding.tvComments.text = "$finalTime\n$content"
                    }
                }

//                val hotSpot = withContext(Dispatchers.IO) {
//                    ServiceRepository.getHotSpots().execute().body()
//                }
//                Log.d(TAG,"diaryContent = $hotSpot")
//                if (hotSpot != null) {
//                    var jsonObj=JSONObject(hotSpot)
//                    var dataObj=jsonObj.optJSONObject("data")
//                    val list= ArrayList<String>()
//                    dataObj.keys().forEach {
//                        if(list.size<=30){
//                            list.add(it)
//                        }
//                    }
//                    val tagAdapter=HotSpotTagsAdapter()
//                    tagAdapter.setData(list)
//                    binding.tag.setAdapter(tagAdapter)
//                }
            }
        }
    }

    private fun updateBgAndPoem(url: String) {
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
                        imgBmp = resource;
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
//                                binding.weather.setBackgroundColor(
//                                    ColorUtils.compositeColors(
//                                        vibrantColor,
//                                        resources.getColor(R.color.brown)
//                                    )
//                                )

                                binding.tvComments.setBackgroundColor(vibrantColor)
//                                binding.gift.setBackgroundColor(vibrantColor)
//                                binding.gift.setfocus
//                                binding.time.setBackgroundColor(vibrantColor)
//                                binding.diary.setBackgroundColor(vibrantColor)
//                                binding.tvPoem.setBackgroundColor(vibrantColor)
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