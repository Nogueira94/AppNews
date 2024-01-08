package com.ngr.appnews.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.ngr.data.model.Article
import com.ngr.appnews.navigation.navtypes.ArticleNavType

const val ARTICLE_ARG = "articleModel"

sealed class Route(
    val route: String,
    val args: List<NamedNavArgument> = emptyList()
) {
    object ArticlesNavigation : Route(route = "articlesNavigation")

    object ArticlesListScreen: Route(route = "articlesListScreen")

    object ArticleDetailScreen: Route(
        route = "articleDetailScreen/{articleModel}",
        args = listOf(
            navArgument(ARTICLE_ARG){
                type = ArticleNavType()
                nullable = false
            }
        )
    ){
        fun navigate(articleDomainModel: Article): String {
            return route.replace("{${args.first().name}}", ArticleNavType.encodeToString(articleDomainModel))
        }
    }
}