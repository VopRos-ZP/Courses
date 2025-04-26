package ru.vopros.courses.domain.repository

import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    val isFirstLaunch: Flow<Boolean>
    val isLogin: Flow<Boolean>

    suspend fun saveIsFirstLaunch(value: Boolean)
    suspend fun saveIsLogin(value: Boolean)
}