package ru.vopros.courses.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import ru.vopros.courses.domain.repository.StoreRepository

class StoreRepositoryImpl(private val context: Context): StoreRepository {

    companion object {
        private const val STORE_NAME = "settings"
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)

        private const val IS_FIRST_LAUNCH_KEY_NAME = "is_first_launch"
        private const val IS_LOGIN_KEY_NAME = "is_login"
        val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey(IS_FIRST_LAUNCH_KEY_NAME)
        val IS_LOGIN_KEY = booleanPreferencesKey(IS_LOGIN_KEY_NAME)
    }

    override val isFirstLaunch = context.dataStore.data.map { it[IS_FIRST_LAUNCH_KEY] ?: true }

    override val isLogin = context.dataStore.data.map { it[IS_LOGIN_KEY] ?: false }

    override suspend fun saveIsFirstLaunch(value: Boolean) {
        context.dataStore.edit { it[IS_FIRST_LAUNCH_KEY] = value }
    }

    override suspend fun saveIsLogin(value: Boolean) {
        context.dataStore.edit { it[IS_LOGIN_KEY] = value }
    }

}