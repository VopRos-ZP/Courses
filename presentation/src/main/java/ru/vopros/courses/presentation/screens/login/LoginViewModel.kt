package ru.vopros.courses.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vopros.courses.domain.Utils
import ru.vopros.courses.domain.repository.StoreRepository

class LoginViewModel(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    fun onEmailChange(value: String) {
        val sanitizedValue = value.filter { it.isAsciiPrintable() }
        _state.update {
            it.copy(
                email = sanitizedValue,
                isValid = validateInputs(sanitizedValue, it.password)
            )
        }
    }

    fun onPasswordChange(value: String) {
        _state.update {
            it.copy(
                password = value,
                isValid = validateInputs(it.email, value)
            )
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        return email.isValidEmail() && password.length >= 6
    }

    private fun String.isValidEmail(): Boolean {
        return matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex())
    }

    private fun Char.isAsciiPrintable(): Boolean {
        return code in 32..126
    }

    fun onLoginClick() {
        viewModelScope.launch {
            try {
                storeRepository.saveIsLogin(true)
                _state.update { it.copy(isSuccess = true) }
            } catch (e: Exception) {
                _state.update { it.copy(isSuccess = false) }
            }
        }
    }

    fun onVkClick() {
        _state.update { it.copy(url = Utils.VK_URL) }
    }

    fun onOkClick() {
        _state.update { it.copy(url = Utils.OK_URL) }
    }

}