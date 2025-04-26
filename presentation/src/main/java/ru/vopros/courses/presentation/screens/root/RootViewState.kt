package ru.vopros.courses.presentation.screens.root

data class RootViewState(
    val isReady: Boolean = false,
    val isFirstLaunch: Boolean = true,
    val isLogin: Boolean = false
)
