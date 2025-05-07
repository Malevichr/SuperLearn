package ru.malevichrp.superlearn.data.learn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey


@Entity("Questions",
    foreignKeys = [
        ForeignKey(
            entity = ThemeCache::class,
            parentColumns = ["id"],
            childColumns = ["theme_id"],
            onDelete = CASCADE
        )
    ])
data class QuestionCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("theme_id")
    val themeId: Int,
    @ColumnInfo("question")
    val question: String,
    @ColumnInfo("correct")
    val correctAnswer: String
)

@Entity("Incorrects",
    foreignKeys = [
        ForeignKey(
            entity = QuestionCache::class,
            parentColumns = ["id"],
            childColumns = ["question_id"],
            onDelete = CASCADE
        )
    ])
data class IncorrectCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("incorrect_id")
    val id: Int = 0,
    @ColumnInfo("question_id")
    val questionId: Int,
    @ColumnInfo("text")
    val choice: String
)