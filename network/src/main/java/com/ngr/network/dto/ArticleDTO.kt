package com.ngr.network.dto

import com.ngr.data.model.Article
import com.ngr.network.config.Mapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDTO (
    @SerialName("source") var source: SourceDTO = SourceDTO(),
    @SerialName("author") var author: String? = "",
    @SerialName("title") var title: String? = "",
    @SerialName("description") var description: String? = "",
    @SerialName("url") var url: String? = "",
    @SerialName("urlToImage") var urlToImage: String? = "",
    @SerialName("publishedAt") var publishedAt: String? = "",
    @SerialName("content") var content: String? = ""
)

class ArticleMapper(private val sourceMapper: SourceMapper) : Mapper<Article, ArticleDTO> {
    override fun mapToDomainModel(dto: ArticleDTO) = Article(
        source = sourceMapper.mapToDomainModel(dto.source),
        author = dto.author ?: "",
        title = dto.title ?: "",
        description = dto.description ?: "",
        url = dto.url ?: "",
        urlToImage = dto.urlToImage ?: "",
        publishedAt = dto.publishedAt ?: "",
        content = dto.content ?: ""
    )

}