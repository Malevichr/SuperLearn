package ru.malevichrp.superlearn.fragments.quiz.game.data

data class QuestionAndChoices(
    val question: String,
    val listOf: List<String>,
    val correctIndex: Int
)