package ru.malevichrp.superlearn.core.data

import android.content.SharedPreferences

interface IntCache {
    fun save(newValue: Int)
    fun read(): Int
    fun increment(): Int
    fun default(): Int
    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Int = 0,
    ) : IntCache {
        override fun save(newValue: Int) {
            sharedPreferences.edit().putInt(key, newValue).apply()
        }

        override fun read(): Int {
            return sharedPreferences.getInt(key, defaultValue)
        }

        override fun increment(): Int {
            val newValue = read() + 1
            save(newValue)
            return newValue
        }

        override fun default(): Int {
            save(defaultValue)
            return defaultValue
        }

    }
}