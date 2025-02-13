package com.example.ishopping.data.source.repoistory

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

import javax.inject.Inject

class SettingRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun updateDarkModeId(darkModeId: Int) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_ID] = darkModeId
        }
    }

    suspend fun flowDarkModeIds(): Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading dark mode id preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[DARK_MODE_ID] ?: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            } else {
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }
        }

    companion object {
        private val DARK_MODE_ID = intPreferencesKey("dark_mode_id")
    }
}
