package ru.malevichrp.superlearn.fragments.quiz.load.data.cache

import ru.malevichrp.superlearn.fragments.quiz.load.data.cloud.QuestionAndChoicesCloud
import ru.malevichrp.superlearn.fragments.quiz.load.data.cloud.QuizService

interface CloudDataSource {
    suspend fun load(): List<QuestionAndChoicesCloud>

    class Base(
        private val service: QuizService,
        private val size: Int,
    ) : CloudDataSource {
        override suspend fun load(): List<QuestionAndChoicesCloud> {
            val result = service.questionAndChoices(size).execute()
            if (result.isSuccessful) {
                val body = result.body()!!
                if (body.responseCode == 0) {
                    val list = body.dataList
                    if (list.isEmpty()) {
                        throw IllegalStateException("empty Data")
                    } else {
                        return list
                    }
                } else
                    throw IllegalArgumentException("wrong response")

            } else
                throw IllegalStateException("not successful response")
        }
    }
}