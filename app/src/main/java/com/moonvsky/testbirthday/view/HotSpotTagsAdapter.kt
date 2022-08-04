package com.moonvsky.testbirthday.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.moonvsky.testbirthday.R
import com.moxun.tagcloudlib.view.TagsAdapter

 class HotSpotTagsAdapter: TagsAdapter() {
     private lateinit var mList:List<String>
     fun setData(list: List<String>){
         mList=list;
     }
    override fun getCount(): Int {
        return mList.size
    }

    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {
        var tvTag=View.inflate(context, R.layout.item_tag,null) as TextView
        tvTag.text=mList[position]
        return tvTag
    }

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getPopularity(position: Int): Int {
        return 1
    }

    override fun onThemeColorChanged(view: View?, themeColor: Int) {
    }
}