package ru.malevichrp.superlearn.fragments.editQuestion

import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.QuestionAndChoicesDao
import ru.malevichrp.superlearn.data.learn.QuestionCache

interface EditQuestionRepository {
    suspend fun targetQuestion(): Question
    suspend fun deleteTargetQuestion()
    suspend fun updateQuestion(question: Question)
    fun createNew()

    class Base(
        private val targetThemeId: IntCache,
        private val targetEditQuestionId: IntCache,
        private val questionAndChoicesDao: QuestionAndChoicesDao
    ) : EditQuestionRepository {
        override suspend fun targetQuestion(): Question {
            if (targetEditQuestionId.read() == -1) {
                val questionCache = QuestionCache(
                    themeId = targetThemeId.read(),
                    question = "",
                    answer1 = "",
                    answer2 = "",
                    answer3 = "",
                    answer4 = "",
                    correctIndex = 0
                )
                val questionId = questionAndChoicesDao.saveQuestion(questionCache).toInt()

                targetEditQuestionId.save(questionId)
                return questionCache.toQuestion()
            } else {
                val questionId = targetEditQuestionId.read()
                val question = questionAndChoicesDao.question(questionId)
                return question.toQuestion()
            }
        }

        override suspend fun deleteTargetQuestion() {
            questionAndChoicesDao.deleteQuestionById(targetEditQuestionId.read())
            targetEditQuestionId.default()
        }

        override suspend fun updateQuestion(question: Question) {
            val questionId = targetEditQuestionId.read()
            questionAndChoicesDao.updateQuestion(
                question.toQuestionCache(questionId, targetThemeId.read())
            )
        }

        override fun createNew() {
            targetEditQuestionId.default()
        }
    }
}
