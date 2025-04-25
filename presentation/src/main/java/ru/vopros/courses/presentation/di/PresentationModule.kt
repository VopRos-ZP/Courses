package ru.vopros.courses.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.vopros.courses.presentation.screens.login.LoginViewModel
import ru.vopros.courses.presentation.screens.main.MainViewModel
import ru.vopros.courses.presentation.screens.onboarding.OnboardingViewModel

private val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::MainViewModel)
}

val presentationModule = module {
    includes(
        viewModelModule
    )
}