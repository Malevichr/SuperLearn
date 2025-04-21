package ru.malevichrp.superlearn.fragments.editTest

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.StringCache

interface EditTestRepository {
    suspend fun loadQuestions(): ArrayList<CharSequence>
    fun changeTargetEdiQuestion(questionText: String, isNew: Boolean)
    class Fake(
        private val targetEditQuestionIsNew: BooleanCache,
        private val targetEditQuestionText: StringCache
    ) : EditTestRepository {
        override suspend fun loadQuestions(): ArrayList<CharSequence> {
            return questions
        }

        override fun changeTargetEdiQuestion(questionText: String, isNew: Boolean) {
            targetEditQuestionIsNew.save(isNew)
            targetEditQuestionText.save(questionText)
        }

        companion object {
            val questions = arrayListOf<CharSequence>("Первый", "Второй", "Третий")
        }
    }
}
