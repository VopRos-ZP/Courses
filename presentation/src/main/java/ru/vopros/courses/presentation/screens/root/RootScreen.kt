package ru.vopros.courses.presentation.screens.root

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.vopros.courses.presentation.components.Loader
import ru.vopros.courses.presentation.screens.login.Login
import ru.vopros.courses.presentation.screens.login.LoginScreen
import ru.vopros.courses.presentation.screens.main.Main
import ru.vopros.courses.presentation.screens.main.MainScreen
import ru.vopros.courses.presentation.screens.onboarding.Onboarding
import ru.vopros.courses.presentation.screens.onboarding.OnboardingScreen

@Composable
fun RootScreen(viewModel: RootViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsState()

    Crossfade(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        targetState = state.isReady
    ) { isReady ->
        if (!isReady) Loader()
        else {
            NavHost(
                navController = navController,
                startDestination = when {
                    state.isFirstLaunch -> Onboarding
                    !state.isLogin -> Login
                    else -> Main
                }
            ) {
                composable<Onboarding> {
                    OnboardingScreen(
                        onContinueClick = {
                            when {
                                state.isLogin -> navController.navigate(Main) {
                                    popUpTo<Onboarding> { inclusive = true }
                                }
                                else -> navController.navigate(Login) {
                                    popUpTo<Onboarding> { inclusive = true }
                                }
                            }
                        }
                    )
                }
                composable<Login> {
                    LoginScreen(
                        onLoginClick = {
                            navController.navigate(Main) {
                                popUpTo<Login> { inclusive = true }
                            }
                        }
                    )
                }
                composable<Main> {
                    MainScreen()
                }
            }
        }
    }
}