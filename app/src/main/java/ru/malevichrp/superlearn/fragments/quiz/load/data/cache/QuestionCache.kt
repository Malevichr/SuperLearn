package ru.malevichrp.superlearn.fragments.quiz.load.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Questions")
data class QuestionCache(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("question")
    val question: String,
    @ColumnInfo("correct")
    val correctAnswer: String
)

@Entity("Incorrects", primaryKeys = ["question_id", "text"])
data class IncorrectCache(
    @ColumnInfo("question_id")
    val questionId: Int,
    @ColumnInfo("text")
    val choice: String
)