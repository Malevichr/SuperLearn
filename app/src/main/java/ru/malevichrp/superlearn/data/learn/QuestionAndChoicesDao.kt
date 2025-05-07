package ru.malevichrp.superlearn.data.learn

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionAndChoicesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestions(questions: List<QuestionCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestion(question: QuestionCache) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveIncorrects(incorects: List<IncorrectCache>)

    @Query("SELECT * FROM Questions WHERE id =:id")
    suspend fun question(id: Int): QuestionCache

    @Query("SELECT * FROM Incorrects WHERE question_id = :questionId")
    suspend fun incorects(questionId: Int): List<IncorrectCache>

    @Query("SELECT * FROM Questions WHERE theme_id =:themeId")
    suspend fun questionsByParent(themeId: Int): List<QuestionCache>

    @Update
    suspend fun updateQuestion(question: QuestionCache)

    @Update
    suspend fun updateIncorrects(incorrects: List<IncorrectCache>)

    @Query(
        """
        SELECT * FROM Questions 
        WHERE theme_id = :themeId 
        ORDER BY id 
        LIMIT 1 OFFSET :index
        """
    )
    suspend fun getQuestionByThemeAndIndex(
        themeId: Int,
        index: Int
    ): QuestionCache?
}