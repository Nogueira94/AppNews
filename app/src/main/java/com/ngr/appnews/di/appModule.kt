package com.ngr.appnews.di

import com.ngr.appnews.BuildConfig
import com.ngr.appnews.viewmodel.ListArticlesViewModel
import com.ngr.appnews.viewmodel.MainActivityViewModel
import com.ngr.network.config.NetworkClient
import com.ngr.network.config.NetworkClientFactory
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    factory<NetworkClient<Retrofit>> {
        NetworkClientFactory().client(BuildConfig.API_KEY, BuildConfig.SOURCE_PARAM)
    }

    viewModelOf(::ListArticlesViewModel)
    viewModelOf(::MainActivityViewModel)

}