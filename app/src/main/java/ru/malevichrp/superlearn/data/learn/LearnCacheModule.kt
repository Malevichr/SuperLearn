package ru.malevichrp.superlearn.data.learn

import android.content.Context
import androidx.room.Room

interface LearnCacheModule {
    fun questionAndChoicesDao(): QuestionAndChoicesDao
    fun learnDao(): LearnDao
    fun clearDatabase(): ClearDatabase
    class Base(applicationContext: Context) :
        LearnCacheModule {
        private val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                QuestionAndChoicesDatabase::class.java,
                "question-and-choices-database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        override fun questionAndChoicesDao(): QuestionAndChoicesDao {
            return database.questionAndChoicesDao()
        }

        override fun learnDao(): LearnDao {
            return database.learnDao()
        }

        override fun clearDatabase(): ClearDatabase = database
    }
}