package com.ngr.network.datasource

import com.ngr.data.model.TopHeadlines
import com.ngr.network.dto.TopHeadlinesMapper
import com.ngr.network.config.DataResponse
import com.ngr.network.service.TopHeadlinesService

class TopHeadlinesDataSource(
    private val service: TopHeadlinesService,
    private val topHeadlinesMapper: TopHeadlinesMapper
) : DataSource() {
    suspend fun getTopHeadlines(): DataResponse<TopHeadlines> {
        return apiCall({ service.getTopHeadlines() }, topHeadlinesMapper)
    }
}