package ru.malevichrp.superlearn.fragments.editQuestion

import ru.malevichrp.superlearn.core.data.StringCache

interface EditQuestionRepository {
    fun targetQuestion() : Question
    fun deleteTargetQuestion()
    fun saveQuestion(question: Question)

    class Fake(
        private val targetQuestionText: StringCache
    ): EditQuestionRepository{
        override fun targetQuestion(): Question {
            return Question(
                title = "Бу!",
                answer1 = "Испугался?",
                answer2 = "Не бойся меня",
                answer3 = "Давай смотреть",
                answer4 = "Не хочешь?",
                rightAnswerIndex = 2
            )
        }

        override fun deleteTargetQuestion() {

        }

        override fun saveQuestion(question: Question) {

        }
    }
}
