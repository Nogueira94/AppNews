package com.ngr.network.client

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ngr.network.config.NetworkClient
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val NEWS_API_URL = "https://newsapi.org/"
private const val API_VERSION = "v2/"

class RetrofitClient(apiKey: String) : NetworkClient<Retrofit> {
    private val client = Retrofit.Builder()
        .baseUrl(NEWS_API_URL + API_VERSION)
        .client(getOkHttpClient(apiKey))
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    override fun client(): Retrofit = client

    private fun getOkHttpClient(apiKey: String): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val headerInterceptor = Interceptor{ chain ->
            val builder = chain.request().newBuilder()
                .addHeader("X-Api-Key",apiKey)
                .build()
            chain.proceed(builder)
        }

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

}