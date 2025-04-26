package ru.vopros.courses.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.vopros.courses.presentation.screens.courses.CoursesViewModel
import ru.vopros.courses.presentation.screens.favorites.FavoritesViewModel
import ru.vopros.courses.presentation.screens.login.LoginViewModel
import ru.vopros.courses.presentation.screens.onboarding.OnboardingViewModel

private val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::CoursesViewModel)
    viewModelOf(::FavoritesViewModel)
}

val presentationModule = module {
    includes(
        viewModelModule
    )
}