package ru.malevichrp.superlearn.fragments.editQuestion

import ru.malevichrp.superlearn.data.learn.QuestionCache

data class Question(
    val title: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val rightAnswerIndex: Int
) {
    fun toQuestionCache(questionId: Int = -1, themeId: Int) =
        if (questionId == -1) QuestionCache(
            themeId = themeId,
            question = title,
            answer1 = answer1,
            answer2 = answer2,
            answer3 = answer3,
            answer4 = answer4,
            correctIndex = rightAnswerIndex
        ) else
            QuestionCache(
                id = questionId,
                themeId = themeId,
                question = title,
                answer1 = answer1,
                answer2 = answer2,
                answer3 = answer3,
                answer4 = answer4,
                correctIndex = rightAnswerIndex
            )
}
