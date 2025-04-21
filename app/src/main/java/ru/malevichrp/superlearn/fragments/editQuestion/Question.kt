package ru.malevichrp.superlearn.fragments.editQuestion

data class Question(
    val title: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val rightAnswerIndex: Int
)
