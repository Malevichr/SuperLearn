package ru.malevichrp.superlearn.fragments.quiz.load.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.IncorrectCache
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.QuestionCache

@Dao
interface QuestionAndChoicesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestions(questions: List<QuestionCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveIncorrects(incorects: List<IncorrectCache>)

    @Query("SELECT * FROM Questions WHERE id =:id")
    suspend fun question(id: Int): QuestionCache

    @Query("SELECT * FROM Incorrects WHERE question_id = :questionId")
    suspend fun incorects(questionId: Int): List<IncorrectCache>
}