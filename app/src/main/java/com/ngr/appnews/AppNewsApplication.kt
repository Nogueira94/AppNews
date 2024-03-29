package com.ngr.appnews

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.ngr.appnews.di.appModule
import com.ngr.domain.di.domainModule
import com.ngr.network.di.topHeadlinesApiModule
import com.ngr.security.di.securityModule
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
                securityModule,
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
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .placeholder(AppCompatResources.getDrawable(this, com.ngr.designsystem.R.drawable.loading_image_placeholder))
            .error(AppCompatResources.getDrawable(this, com.ngr.designsystem.R.drawable.no_image_placeholder))
            .respectCacheHeaders(false)
            .build()

    }

    companion object{
        var ALREADY_AUTH = false
    }
}