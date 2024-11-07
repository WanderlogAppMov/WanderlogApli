package com.example.wanderlog.io.response

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object PreferenceHelper {
    fun defaultPrefs(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    operator fun SharedPreferences.get(key: String, defaultValue: String): String {
        return getString(key, defaultValue) ?: defaultValue
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        edit().apply {
            when (value) {
                is String? -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }.apply()
    }
}