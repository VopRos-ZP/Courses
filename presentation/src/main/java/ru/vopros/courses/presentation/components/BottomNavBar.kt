package ru.vopros.courses.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.vopros.courses.presentation.R
import ru.vopros.courses.presentation.screens.account.Account
import ru.vopros.courses.presentation.screens.courses.Courses
import ru.vopros.courses.presentation.screens.favorites.Favorites
import ru.vopros.courses.presentation.theme.DarkGrey
import ru.vopros.courses.presentation.theme.Green
import ru.vopros.courses.presentation.theme.LightGrey
import ru.vopros.courses.presentation.theme.White

data class TopLevelRoute<T : Any>(val name: String, val route: T, @DrawableRes val icon: Int)

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topLevelRoutes = listOf(
        TopLevelRoute("Главная", Courses, R.drawable.house),
        TopLevelRoute("Избранное", Favorites, R.drawable.bookmark),
        TopLevelRoute("Аккаунт", Account, R.drawable.person),
    )

    NavigationBar(
        modifier = Modifier,
        containerColor = DarkGrey,
        contentColor = White,
    ) {
        topLevelRoutes.map { topLevelRoute ->
            NavigationBarItem(
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(topLevelRoute.icon),
                        contentDescription = topLevelRoute.name
                    )
                },
                label = {
                    Text(
                        text = topLevelRoute.name,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green,
                    selectedTextColor = Green,
                    indicatorColor = LightGrey,
                    unselectedIconColor = White,
                    unselectedTextColor = White,
                    disabledIconColor = White.copy(alpha = 0.5f),
                    disabledTextColor = White.copy(alpha = 0.5f),
                )
            )
        }
    }
}