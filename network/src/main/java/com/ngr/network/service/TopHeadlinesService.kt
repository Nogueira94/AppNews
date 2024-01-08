package com.ngr.network.service

import com.ngr.network.dto.TopHeadlinesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us"
    ): TopHeadlinesDTO
}
