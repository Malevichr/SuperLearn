package ru.malevichrp.superlearn.data.learn

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionCache::class, ThemeCache::class, TheoryCache::class], version = 5)
abstract class QuestionAndChoicesDatabase : RoomDatabase(), ClearDatabase {
    abstract fun questionAndChoicesDao(): QuestionAndChoicesDao
    abstract fun learnDao(): LearnDao
    abstract fun theoryDao(): TheoryDao
    override suspend fun clear() = clearAllTables()
}

interface ClearDatabase {
    suspend fun clear()
}