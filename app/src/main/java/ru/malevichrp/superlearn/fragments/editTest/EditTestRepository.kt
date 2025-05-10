package ru.malevichrp.superlearn.fragments.editTest

import android.util.Log
import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.QuestionAndChoicesDao
import ru.malevichrp.superlearn.views.recycler.TextItems

interface EditTestRepository {
    suspend fun loadQuestions(): TextItems
    fun changeTargetEdiQuestion(questionId: Int, isNew: Boolean)
    class Fake(
        private val targetEditQuestionIsNew: BooleanCache,
        private val targetEditQuestionId: IntCache,

        ) : EditTestRepository {
        override suspend fun loadQuestions(): TextItems {
            return questions
        }

        override fun changeTargetEdiQuestion(questionId: Int, isNew: Boolean) {
            targetEditQuestionIsNew.save(isNew)
            targetEditQuestionId.save(questionId)
        }

        companion object {

            val questions = TextItems()
                .add("Первый")
                .add("Второй")
                .add("Третий")
        }
    }
    class Base(
        private val targetThemeId: IntCache,
        private val targetEditQuestionIsNew: BooleanCache,
        private val targetEditQuestionId: IntCache,
        private val questionAndChoicesDao: QuestionAndChoicesDao
    ) : EditTestRepository {
        override suspend fun loadQuestions(): TextItems {
            val themes = questionAndChoicesDao.questionsByParent(targetThemeId.read())
            val textItems = TextItems()
            themes.forEach {
                textItems.textTitles.add(it.question)
                textItems.itemIds.add(it.id)
            }
            Log.d("mlvc", targetThemeId.read().toString())

            return textItems
        }

        override fun changeTargetEdiQuestion(questionId: Int, isNew: Boolean) {
            targetEditQuestionIsNew.save(isNew)
            targetEditQuestionId.save(questionId)
        }
    }
}
