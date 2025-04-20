package ru.malevichrp.superlearn.core.data

import android.content.SharedPreferences

interface BooleanCache {
    fun save(value: Boolean)
    fun read(): Boolean

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Boolean = false
    ) : BooleanCache {
        override fun save(value: Boolean) {
            sharedPreferences.edit().putBoolean(key, value).apply()
        }

        override fun read(): Boolean =
            sharedPreferences.getBoolean(key, defaultValue)
    }
}