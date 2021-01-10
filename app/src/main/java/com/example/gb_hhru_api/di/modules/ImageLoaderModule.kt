package com.example.gb_hhru_api.di.modules

import android.widget.ImageView
import com.example.gb_hhru_api.mvp.model.cache.IImageCache
import com.example.gb_hhru_api.mvp.model.image.IImageLoader
import com.example.gb_hhru_api.mvp.model.network.INetworkStatus
import com.example.gb_hhru_api.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {
    @Singleton
    @Provides
    fun imageLoader(networkStatus: INetworkStatus, cache: IImageCache): IImageLoader<ImageView> = GlideImageLoader(networkStatus, cache)
}