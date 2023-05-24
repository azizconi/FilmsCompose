package com.example.filmscompose.feature_app.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.filmscompose.feature_app.presentation.ui.screen.film.FilmScreen
import com.example.filmscompose.feature_app.presentation.ui.screen.main.MainScreen

@Composable
fun AppNavigation(
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main) {
        composable(Screen.Main) {
            MainScreen(navController = navController)
        }

        composable(
            Screen.Film + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id")
            id?.let {
                FilmScreen(id = id, navController = navController)
            }
        }


    }

}