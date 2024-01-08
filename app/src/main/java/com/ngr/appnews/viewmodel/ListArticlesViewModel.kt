package com.ngr.appnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngr.data.model.Article
import com.ngr.domain.usecase.ArticlesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class ListArticleState(
    val articles : List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Throwable? = null,
)

sealed class ListArticleEvent {
    object Refresh: ListArticleEvent()
}

class ListArticlesViewModel(
    private val articlesListUseCase: ArticlesListUseCase
) : ViewModel() {

    private val mutableViewState: MutableStateFlow<ListArticleState> =
        MutableStateFlow(ListArticleState(isLoading = true))

    val viewState: MutableStateFlow<ListArticleState>
        get() = mutableViewState

    init {
        getArticles()
    }

    fun dispatch(event: ListArticleEvent){
        when(event){
            ListArticleEvent.Refresh -> getArticles()
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            articlesListUseCase.invoke()
                .onStart {
                    mutableViewState.value = mutableViewState.value.copy(
                        isLoading = true,
                        articles = emptyList(),
                        isError = null
                    )
                }
                .catch {
                    mutableViewState.value = mutableViewState.value.copy(
                        isError = it,
                        articles = emptyList(),
                        isLoading = false,
                    )
                }
                .collect{articlesList ->
                    mutableViewState.value = mutableViewState.value.copy(
                        articles = articlesList,
                        isError = null,
                        isLoading = false
                    )
                }
        }
    }

}