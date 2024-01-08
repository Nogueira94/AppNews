package com.ngr.domain

import com.ngr.common.utils.formatDate
import com.ngr.data.model.Article
import com.ngr.data.model.Source
import com.ngr.domain.repository.TopHeadlinesRepository
import com.ngr.domain.usecase.ArticlesListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticlesListUseCaseTest {

    @Test
    fun `return sorted articles`() = runBlocking {
        val mockRepository = mockk<TopHeadlinesRepository>()
        val useCase = ArticlesListUseCase(mockRepository)

        val unsortedArticles = listOf(
            Article(
                source = Source("", ""),
                title = "06/01/2023",
                author = "Author Preview",
                description = "Description Preview",
                url = "Url Preview",
                content = "Content Preview",
                publishedAt = "2023-01-06T19:00:08Z",
                urlToImage = "Url Preview"
            ), Article(
                source = Source("", ""),
                title = "06/01/2024",
                author = "Author Preview",
                description = "Description Preview",
                url = "Url Preview",
                content = "Content Preview",
                publishedAt = "2024-01-06T19:00:08Z",
                urlToImage = "Url Preview"
            ), Article(
                source = Source("", ""),
                title = "07/01/2024",
                author = "Author Preview",
                description = "Description Preview",
                url = "Url Preview",
                content = "Content Preview",
                publishedAt = "2024-01-07T19:00:08Z",
                urlToImage = "Url Preview"
            )
        )

        val sortedArticles = unsortedArticles.sortedByDescending {
            formatDate(it.publishedAt)
        }

        coEvery { mockRepository.getTopHeadlines() } returns flow {
            emit(sortedArticles)
        }

        val result = useCase().toList()

        assertEquals(listOf(sortedArticles), result)
    }
}