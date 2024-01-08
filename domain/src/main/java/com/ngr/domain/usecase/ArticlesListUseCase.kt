package com.ngr.domain.usecase

import com.ngr.data.model.Article
import com.ngr.domain.repository.TopHeadlinesRepository
import com.ngr.common.utils.formatDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesListUseCase(
    private val repository: TopHeadlinesRepository,
) {
    operator fun invoke(): Flow<List<Article>> = repository.getTopHeadlines().map {
        it.sortedByDescending { article ->
            val date = formatDate(article.publishedAt)
            date
        }
    }

}