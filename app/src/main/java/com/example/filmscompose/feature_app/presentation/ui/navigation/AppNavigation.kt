package com.example.filmscompose.feature_app.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.filmscompose.feature_app.presentation.ui.screen.main.MainScreen

@Composable
fun AppNavigation(
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main) {
        composable(Screen.Main) {
            MainScreen()
        }
    }
    
}