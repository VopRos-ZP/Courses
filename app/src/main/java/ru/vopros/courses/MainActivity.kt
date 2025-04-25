package ru.vopros.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.compose.KoinAndroidContext
import ru.vopros.courses.presentation.screens.login.LoginScreen
import ru.vopros.courses.presentation.screens.main.MainScreen
import ru.vopros.courses.presentation.screens.onboarding.OnboardingScreen
import ru.vopros.courses.presentation.theme.CoursesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                CoursesTheme {
                    LoginScreen()
                }
            }
        }
    }

}


