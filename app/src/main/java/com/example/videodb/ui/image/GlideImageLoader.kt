package com.example.videodb.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.videodb.mvp.model.image.IImageLoader


class GlideImageLoader(val baseURL:String) : IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {

        Glide.with(container.context)
            .asBitmap()
            .load(baseURL+url)
            .into(container)
    }

}