package ru.vopros.courses.presentation.screens.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vopros.courses.domain.repository.StoreRepository

class RootViewModel(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RootViewState())
    val state = _state.asStateFlow()

    init {
        initLocalConfig()
    }

    private fun initLocalConfig() {
        viewModelScope.launch {
            combine(storeRepository.isFirstLaunch, storeRepository.isLogin) { isFirstLaunch, isLogin ->
                isFirstLaunch to isLogin
            }.collect { (isFirstLaunch, isLogin) ->
                delay(1000) // Чтобы CircularProgressIndicator был анимирован
                _state.update {
                    it.copy(
                        isFirstLaunch = isFirstLaunch,
                        isLogin = isLogin,
                        isReady = true
                    )
                }
            }
        }
    }

}