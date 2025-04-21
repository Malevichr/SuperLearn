package ru.malevichrp.superlearn.fragments.quiz.load.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionCache::class, IncorrectCache::class], version = 1)
abstract class QuestionAndChoicesDatabase : RoomDatabase(), ClearDatabase {
    abstract fun dao(): QuestionAndChoicesDao
    override suspend fun clear() = clearAllTables()
}

interface ClearDatabase {
    suspend fun clear()
}