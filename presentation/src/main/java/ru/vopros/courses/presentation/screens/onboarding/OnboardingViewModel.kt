package ru.vopros.courses.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vopros.courses.domain.repository.StoreRepository

class OnboardingViewModel(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingViewState())
    val state = _state.asStateFlow()

    fun onContinueClick() {
        viewModelScope.launch {
            try {
                storeRepository.saveIsFirstLaunch(false)
                _state.update { it.copy(isSuccess = true) }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.update { it.copy(isSuccess = false) }
            }
        }
    }

}