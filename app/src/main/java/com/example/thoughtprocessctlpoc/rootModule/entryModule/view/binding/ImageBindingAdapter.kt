package com.example.thoughtprocessctlpoc.rootModule.entryModule.view.binding

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

object ImageBindingAdapter {
   @JvmStatic
    @BindingAdapter("setImageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("setDateTime")
    fun setDateTime(view: TextView,title:String)
    {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        view.text=currentDate
    }

}