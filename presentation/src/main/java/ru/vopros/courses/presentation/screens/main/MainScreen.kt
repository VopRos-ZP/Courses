package ru.vopros.courses.presentation.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.vopros.courses.presentation.components.BottomNavBar
import ru.vopros.courses.presentation.screens.account.Account
import ru.vopros.courses.presentation.screens.courses.Courses
import ru.vopros.courses.presentation.screens.courses.CoursesScreen
import ru.vopros.courses.presentation.screens.favorites.Favorites
import ru.vopros.courses.presentation.screens.favorites.FavoritesScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = Courses,
        ) {
            composable<Courses> {
                CoursesScreen()
            }
            composable<Favorites> {
                FavoritesScreen()
            }
            composable<Account> {

            }
        }
    }
}

