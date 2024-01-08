package com.ngr.network.config

import android.adservices.measurement.WebSourceParams
import com.ngr.network.client.RetrofitClient
import retrofit2.Retrofit

interface NetworkClient<Client> {
    fun client() : Client
    fun sourceParam() : String
}

@Suppress("UNCHECKED_CAST")
class NetworkClientFactory{
    inline fun <reified Client> client(apiKey: String, sourceParam: String = "") : NetworkClient<Client> {
        return when (Client::class){
            Retrofit::class -> RetrofitClient(apiKey, sourceParam = sourceParam) as NetworkClient<Client>
            else -> throw IllegalStateException("Unknown Client")
        }
    }
}