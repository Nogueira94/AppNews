package com.ngr.appnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import com.ngr.appnews.navigation.NavGraph
import com.ngr.designsystem.theme.AppNewsTheme
import com.ngr.appnews.navigation.Route
import com.ngr.security.biometrics.BiometricAuth
import org.koin.android.ext.android.inject

class MainActivity : FragmentActivity() {

    private val biometricAuth: BiometricAuth by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biometricAuth.canAccess(this) { canAccess ->
            if (canAccess) {
                setContent {
                    AppNewsTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(), color = Color.Black
                        ) {
                            NavGraph(startDestination = Route.ArticlesNavigation.route)
                        }
                    }
                }
            } else {
                finish()
            }
        }
    }
}
