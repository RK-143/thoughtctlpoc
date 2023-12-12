package com.example.thoughtprocessctlpoc.rootModule.entryModule.view.binding

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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

}