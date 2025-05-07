package ru.malevichrp.superlearn.fragments.editQuestion

import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.IncorrectCache
import ru.malevichrp.superlearn.data.learn.QuestionAndChoicesDao
import ru.malevichrp.superlearn.data.learn.QuestionCache

interface EditQuestionRepository {
    suspend fun targetQuestion(): Question
    fun deleteTargetQuestion()
    suspend fun updateQuestion(question: Question)


    class Base(
        private val targetThemeId: IntCache,
        private val targetEditQuestionId: IntCache,
        private val questionAndChoicesDao: QuestionAndChoicesDao
    ) : EditQuestionRepository {
        override suspend fun targetQuestion(): Question {
            if (targetEditQuestionId.read() == -1) {
                val questionId = questionAndChoicesDao.saveQuestion(
                    QuestionCache(
                        themeId = targetThemeId.read(),
                        question = "",
                        correctAnswer = ""
                    )
                ).toInt()
                questionAndChoicesDao.saveIncorrects(
                        listOf(
                            IncorrectCache(questionId = questionId, choice = ""),
                            IncorrectCache(questionId = questionId, choice = ""),
                            IncorrectCache(questionId = questionId, choice = ""),
                        )
                )
                targetEditQuestionId.save(questionId)
                return Question(
                    title = "New Question",
                    answer1 = "",
                    answer2 = "",
                    answer3 = "",
                    answer4 = "",
                    rightAnswerIndex = 0
                )
            } else {
                val questionId = targetEditQuestionId.read()
                val question = questionAndChoicesDao.question(questionId)
                val incorrects = questionAndChoicesDao.incorects(questionId)
                val choices =
                    (listOf(question.correctAnswer) + incorrects.map { it.choice }).shuffled()

                return Question(
                    title = question.question,
                    answer1 = choices[0],
                    answer2 = choices[1],
                    answer3 = choices[2],
                    answer4 = choices[3],
                    rightAnswerIndex = choices.indexOf(question.correctAnswer)
                )
            }

        }

        override fun deleteTargetQuestion() {
        }

        override suspend fun updateQuestion(question: Question) {
            val questionId = targetEditQuestionId.read()
            val answers = with(question) {
                mutableListOf(answer1, answer2, answer3, answer4)
            }
            val correct = answers[question.rightAnswerIndex]
            questionAndChoicesDao.updateQuestion(
                QuestionCache(
                    id = questionId,
                    themeId = targetThemeId.read(),
                    question = question.title,
                    correctAnswer = correct
                )
            )
            answers.removeAt(question.rightAnswerIndex)
            val incorrects = questionAndChoicesDao.incorects(questionId)
            val newIncorrects = incorrects.mapIndexed { id, it ->
                IncorrectCache(
                    id = it.id,
                    questionId = questionId,
                    choice = answers[id]
                )
            }

            questionAndChoicesDao.updateIncorrects(newIncorrects)
        }
    }
}
