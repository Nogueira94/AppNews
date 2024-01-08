package com.ngr.appnews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ngr.data.model.Article
import com.ngr.appnews.ui.ArticleDetailScreen
import com.ngr.appnews.ui.ArticleListScreen

@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.ArticlesNavigation.route,
            startDestination = Route.ArticlesListScreen.route
        ) {
            composable(route = Route.ArticlesListScreen.route) {
                ArticleListScreen(
                    navigateToArticleDetail = { navController.navToArticleDetail(it) },
                )
            }
            composable(
                route = Route.ArticleDetailScreen.route,
                arguments = Route.ArticleDetailScreen.args
            ) {
                val article = it.arguments?.getParcelable<Article>(ARTICLE_ARG) ?: return@composable
                ArticleDetailScreen(
                    article = article
                )
            }
        }
    }
}

private fun NavController.navToArticleDetail(article: Article){
    navigate(Route.ArticleDetailScreen.navigate(article)){
        launchSingleTop = true
    }
}