import com.ngr.data.model.Article
import com.ngr.data.model.Source
import com.ngr.data.model.TopHeadlines
import com.ngr.network.dto.ArticleDTO
import com.ngr.network.dto.SourceDTO
import com.ngr.network.dto.TopHeadlinesDTO

val topHeadlinesDTO : TopHeadlinesDTO = TopHeadlinesDTO(
    status = "ok",
    totalResults = 3,
    articles = listOf(
        ArticleDTO(
            source = SourceDTO("", ""),
            title = "Article Preview",
            author = "Author Preview",
            description = "Description Preview",
            url = "Url Preview",
            content = "Content Preview",
            publishedAt = "Date Preview",
            urlToImage = "Url Preview"
        ), ArticleDTO(
            source = SourceDTO("", ""),
            title = "Article Preview",
            author = "Author Preview",
            description = "Description Preview",
            url = "Url Preview",
            content = "Content Preview",
            publishedAt = "Date Preview",
            urlToImage = "Url Preview"
        ), ArticleDTO(
            source = SourceDTO("", ""),
            title = "Article Preview",
            author = "Author Preview",
            description = "Description Preview",
            url = "Url Preview",
            content = "Content Preview",
            publishedAt = "Date Preview",
            urlToImage = "Url Preview"
        ),
    )
)

val topHeadlines : TopHeadlines = TopHeadlines(
    status = "ok",
    totalResults = 3,
    articles = listOf(
        Article(
            source = Source("", ""),
            title = "Article Preview",
            author = "Author Preview",
            description = "Description Preview",
            url = "Url Preview",
            content = "Content Preview",
            publishedAt = "Date Preview",
            urlToImage = "Url Preview"
        ), Article(
            source = Source("", ""),
            title = "Article Preview",
            author = "Author Preview",
            description = "Description Preview",
            url = "Url Preview",
            content = "Content Preview",
            publishedAt = "Date Preview",
            urlToImage = "Url Preview"
        ), Article(
            source = Source("", ""),
            title = "Article Preview",
            author = "Author Preview",
            description = "Description Preview",
            url = "Url Preview",
            content = "Content Preview",
            publishedAt = "Date Preview",
            urlToImage = "Url Preview"
        ),
    )
)
