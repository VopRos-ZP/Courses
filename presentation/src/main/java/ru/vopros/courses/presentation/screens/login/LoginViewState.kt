package ru.vopros.courses.presentation.screens.login

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isValid: Boolean = false,
    val isSuccess: Boolean? = null,
    val url: String? = null,
)
