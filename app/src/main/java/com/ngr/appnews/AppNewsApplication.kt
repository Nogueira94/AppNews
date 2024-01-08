package com.ngr.appnews

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.ngr.appnews.di.appModule
import com.ngr.domain.di.domainModule
import com.ngr.network.di.topHeadlinesApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppNewsApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AppNewsApplication)
            modules(
                topHeadlinesApiModule,
                domainModule,
                appModule
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizeBytes(0.1.toInt())
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.DISABLED)
            .logger(DebugLogger())
            .placeholder(AppCompatResources.getDrawable(this, com.ngr.designsystem.R.drawable.loading_image_placeholder))
            .error(AppCompatResources.getDrawable(this, com.ngr.designsystem.R.drawable.no_image_placeholder))
            .build()
    }
}