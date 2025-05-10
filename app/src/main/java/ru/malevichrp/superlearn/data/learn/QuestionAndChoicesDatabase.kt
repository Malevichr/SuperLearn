package ru.malevichrp.superlearn.data.learn

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionCache::class, IncorrectCache::class, ThemeCache::class, TheoryCache::class], version = 3)
abstract class QuestionAndChoicesDatabase : RoomDatabase(), ClearDatabase {
    abstract fun questionAndChoicesDao(): QuestionAndChoicesDao
    abstract fun learnDao(): LearnDao
    override suspend fun clear() = clearAllTables()
}

interface ClearDatabase {
    suspend fun clear()
}