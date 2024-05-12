package com.astecnology.newspluse.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.astecnology.newspluse.domain.manager.LocalUserManager
import com.astecnology.newspluse.util.Constants
import com.astecnology.newspluse.util.Constants.USER_SETTINGS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

/**
 * Implementation of the LocalUserManager interface for managing user preferences locally using DataStore.
 * This class provides methods to save and retrieve user preferences related to app entry.
 * @param context The application context.
 */
class LocalUserManagerImpl(private val context: Context) : LocalUserManager {

    /**
     * Saves the app entry preference to DataStore.
     */
    override suspend fun saveAppEntry() {
        context.dataStore.edit { setting ->
            setting[PreferenceKeys.APP_ENTRY] = true
        }
    }

    /**
     * Reads the app entry preference from DataStore.
     * @return A Flow emitting the value of the app entry preference.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.mapLatest { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }
}

/**
 * Extension property to provide easy access to DataStore<Preferences> from Context.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

/**
 * Object holding keys for preferences.
 */
private object PreferenceKeys {

    /**
     * Key for the app entry preference.
     */
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)

}