package com.ngr.appnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ngr.appnews.navigation.NavGraph
import com.ngr.designsystem.theme.AppNewsTheme
import com.ngr.appnews.navigation.Route

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.Black
                ) {
                    NavGraph(startDestination = Route.ArticlesNavigation.route)
                }
            }
        }
    }
}