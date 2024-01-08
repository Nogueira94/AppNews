package com.ngr.network.dto

import com.ngr.data.model.TopHeadlines
import com.ngr.network.config.Mapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesDTO (
    @SerialName("status") var status: String? = "",
    @SerialName("totalResults") var totalResults: Int? = null,
    @SerialName("articles") var articles: List<ArticleDTO> = arrayListOf()
)

class TopHeadlinesMapper(private val articleMapper: ArticleMapper) : Mapper<TopHeadlines, TopHeadlinesDTO> {
    override fun mapToDomainModel(dto: TopHeadlinesDTO) =
        TopHeadlines(
            status = dto.status ?: "",
            totalResults = dto.totalResults ?: null,
            articles = dto.articles.map { articleDTO ->
                articleMapper.mapToDomainModel(articleDTO)
            }
        )
}
