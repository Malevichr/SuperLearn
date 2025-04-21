package ru.malevichrp.superlearn.fragments.quiz.load.data.cache

import android.content.Context
import androidx.room.Room

interface CacheModule {
    fun dao(): QuestionAndChoicesDao
    fun clearDatabase(): ClearDatabase
    class Base(applicationContext: Context) : CacheModule {
        private val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                QuestionAndChoicesDatabase::class.java,
                "question-and-choices-database"
            ).build()
        }

        override fun dao(): QuestionAndChoicesDao {
            return database.dao()
        }

        override fun clearDatabase(): ClearDatabase = database

    }
}