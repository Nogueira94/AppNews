package com.ngr.appnews.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ngr.appnews.R
import com.ngr.appnews.viewmodel.ListArticleEvent
import com.ngr.appnews.viewmodel.ListArticlesViewModel
import com.ngr.data.model.Article
import com.ngr.data.model.Source
import com.ngr.designsystem.component.TopHeadlinesItem
import com.ngr.designsystem.theme.AppNewsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleListScreen(
    navigateToArticleDetail: (Article) -> Unit,
    viewModel: ListArticlesViewModel = koinViewModel()
) {

    val viewState by viewModel.viewState.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewState.isLoading
    )

    viewState.articles.isNotEmpty().let {
        ArticleListScreen(
            articles = viewState.articles,
            swipeRefreshState = swipeRefreshState,
            dispatch = { viewModel.dispatch(it) },
            onArticleClick = { navigateToArticleDetail.invoke(it) }
        )
    }

    viewState.isError?.let {
        ArticleListScreenError(
            tryAgain = { viewModel.dispatch(it) }
        )
    }

}

@Composable
fun ArticleListScreenError(tryAgain: (ListArticleEvent) -> Unit) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { tryAgain.invoke(ListArticleEvent.Refresh) }) {
            Text(text = stringResource(id = R.string.retry_button))
        }
    }
}

@Composable
private fun ArticleListScreen(
    articles: List<Article>,
    swipeRefreshState: SwipeRefreshState,
    dispatch: (ListArticleEvent) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(top = 12.dp, bottom = 12.dp, left = 16.dp, right = 16.dp)
    ) {
        SwipeRefresh(state = swipeRefreshState,
            onRefresh = { dispatch(ListArticleEvent.Refresh) }) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()
            ) {
                items(articles.size) { i ->
                    val article = articles[i]
                    TopHeadlinesItem(
                        modifier = Modifier.clickable { onArticleClick(article) },
                        article = article
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ArticleListScreenPreview() {
    AppNewsTheme {
        ArticleListScreen(
            listOf(
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
                )
            ),
            SwipeRefreshState(true),
            {},
            {}
        )
    }
}

@Preview
@Composable
private fun ArticleListScreenErrorPreview() {
    AppNewsTheme {
        ArticleListScreenError(tryAgain = {})
    }
}