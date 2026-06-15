package com.policyboss.customer.core.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {

        private val KEY_AUTH_TOKEN =
            stringPreferencesKey("key_auth_token")

        private val KEY_USER_MOBILE =
            stringPreferencesKey("key_user_mobile")

        private val KEY_IS_LOGIN =
            booleanPreferencesKey("key_is_login")
    }

    // =========================================
    // SAVE
    // =========================================

    suspend fun saveAuthToken(token: String) {

        dataStore.edit { pref ->

            pref[KEY_AUTH_TOKEN] = token
        }
    }

    suspend fun saveUserMobile(mobile: String) {

        dataStore.edit { pref ->

            pref[KEY_USER_MOBILE] = mobile
        }
    }

    suspend fun saveLoginState(isLogin: Boolean) {

        dataStore.edit { pref ->

            pref[KEY_IS_LOGIN] = isLogin
        }
    }

    // =========================================
    // GET
    // =========================================

    val authToken: Flow<String> =

        dataStore.data.map { pref ->

            pref[KEY_AUTH_TOKEN] ?: ""
        }

    val userMobile: Flow<String> =

        dataStore.data.map { pref ->

            pref[KEY_USER_MOBILE] ?: ""
        }

    val isLogin: Flow<Boolean> =

        dataStore.data.map { pref ->

            pref[KEY_IS_LOGIN] ?: false
        }

    // =========================================
    // CLEAR
    // =========================================

    suspend fun clearSession() {

        dataStore.edit {

            it.clear()
        }
    }
}