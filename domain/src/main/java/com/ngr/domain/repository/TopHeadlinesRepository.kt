package com.ngr.domain.repository

import com.ngr.data.model.Article
import com.ngr.network.config.DataResponse
import com.ngr.network.datasource.TopHeadlinesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface TopHeadlinesRepository {
    fun getTopHeadlines() : Flow<List<Article>>
}

internal class TopHeadlinesRepositoryImpl(
    private val remoteDataSource: TopHeadlinesDataSource
): TopHeadlinesRepository {
    override fun getTopHeadlines(): Flow<List<Article>> = flow {
        when(val result = remoteDataSource.getTopHeadlines()){
            is DataResponse.Failure -> throw result.error
            is DataResponse.Success -> emit(result.data.articles)
        }
    }

}