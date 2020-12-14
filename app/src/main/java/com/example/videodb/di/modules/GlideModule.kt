package com.example.videodb.di.modules

import android.widget.ImageView
import com.example.videodb.mvp.model.image.IImageLoader
import com.example.videodb.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class GlideModule {

    @Singleton
    @Provides
    fun ImageLoader(@Named("imgUrl") imgURL: String): IImageLoader<ImageView> =
        GlideImageLoader(imgURL)

}