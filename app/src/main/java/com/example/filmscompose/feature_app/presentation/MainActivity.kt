package com.example.filmscompose.feature_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.filmscompose.feature_app.presentation.ui.theme.FilmsComposeTheme
import com.example.filmscompose.feature_app.presentation.ui.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    FilmsComposeTheme(darkTheme = false) {
        AppNavigation()
    }
}
