package com.ngr.appnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import com.ngr.appnews.navigation.NavGraph
import com.ngr.designsystem.theme.AppNewsTheme
import com.ngr.appnews.navigation.Route
import com.ngr.appnews.viewmodel.MainActivityViewModel
import com.ngr.appnews.viewmodel.MainEvent
import com.ngr.security.biometrics.BiometricAuth
import kotlinx.coroutines.withContext

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : FragmentActivity() {

    private val viewModel : MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.Black
                ) {
                    val viewState by viewModel.viewState.collectAsState()

                    viewState.requireBiometric.let {
                        when (it) {
                            true -> NavGraph(startDestination = Route.ArticlesNavigation.route)
                            false -> finish()
                            null -> viewModel.dispatch(MainEvent.Auth(this))
                        }
                    }
                }
            }
        }
    }
}
