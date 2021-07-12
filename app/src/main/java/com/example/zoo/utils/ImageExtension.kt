package com.example.zoo.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadHeaderImage(picUrl: String?, placeHolder: Int) {
    Glide.with(this.context)
        .load(picUrl)
        .centerCrop()
        .placeholder(placeHolder)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

fun ImageView.loadRoundRecImage(picUrl: String?, placeHolder: Int) {
    Glide.with(this.context)
        .load(picUrl)
        .placeholder(placeHolder)
        .apply(RequestOptions.bitmapTransform(RoundedCornerCenterCrop(12)))
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

