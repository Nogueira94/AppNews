package com.ngr.network.config

import com.ngr.network.client.RetrofitClient
import retrofit2.Retrofit

interface NetworkClient<Client> {
    fun client() : Client
}

@Suppress("UNCHECKED_CAST")
class NetworkClientFactory{
    inline fun <reified Client> client(apiKey: String) : NetworkClient<Client> {
        return when (Client::class){
            Retrofit::class -> RetrofitClient(apiKey) as NetworkClient<Client>
            else -> throw IllegalStateException("Unknown Client")
        }
    }
}