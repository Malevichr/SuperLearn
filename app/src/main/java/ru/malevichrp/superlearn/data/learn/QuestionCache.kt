package ru.malevichrp.superlearn.data.learn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import ru.malevichrp.superlearn.fragments.editQuestion.Question
import ru.malevichrp.superlearn.fragments.quiz.game.data.QuestionAndChoices


@Entity(
    "Questions",
    foreignKeys = [
        ForeignKey(
            entity = ThemeCache::class,
            parentColumns = ["id"],
            childColumns = ["theme_id"],
            onDelete = CASCADE
        )
    ]
)
data class QuestionCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("theme_id")
    val themeId: Int,
    @ColumnInfo("question")
    val question: String,
    @ColumnInfo("answer1")
    val answer1: String,
    @ColumnInfo("answer2")
    val answer2: String,
    @ColumnInfo("answer3")
    val answer3: String,
    @ColumnInfo("answer4")
    val answer4: String,
    @ColumnInfo("correct_index")
    val correctIndex: Int
) {
    fun toQuestionAndChoices() =
        QuestionAndChoices(
            question,
            listOf(answer1, answer2, answer3, answer4),
            correctIndex
        )

    fun toQuestion() =
        Question(
            question,
            answer1,
            answer2,
            answer3,
            answer4,
            correctIndex
        )
}
