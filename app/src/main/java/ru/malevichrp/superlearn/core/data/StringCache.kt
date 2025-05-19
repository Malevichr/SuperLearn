package ru.malevichrp.superlearn.core.data

import android.content.SharedPreferences

interface StringCache {
    fun save(newValue: String)
    fun read(): String
    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: String,
    ) : StringCache {
        override fun save(newValue: String) {
            sharedPreferences.edit().putString(key, newValue).apply()
        }

        override fun read(): String {
            return sharedPreferences.getString(key, defaultValue) ?: defaultValue
        }
    }
}