package ru.malevichrp.superlearn.data.learn

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LearnDao {
    @Insert
    suspend fun insertTheme(theme: ThemeCache): Long

    @Delete
    suspend fun deleteTheme(theme: ThemeCache)

    @Update
    suspend fun updateTheme(theme: ThemeCache)

    @Query("SELECT * FROM Themes WHERE id = :id")
    suspend fun themeById(id: Int): ThemeCache

    @Query("DELETE FROM Themes WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM Themes")
    suspend fun themes(): List<ThemeCache>
}