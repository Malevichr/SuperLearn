package ru.malevichrp.superlearn.fragments.quiz.load.data

import kotlinx.coroutines.delay
import okio.IOException
import ru.malevichrp.superlearn.data.learn.IncorrectCache
import ru.malevichrp.superlearn.data.learn.QuestionAndChoicesDao
import ru.malevichrp.superlearn.data.learn.QuestionCache
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.CloudDataSource

interface LoadRepository {

    suspend fun load()

    class Base(
        private val dao: QuestionAndChoicesDao,
        private val cloudDataSource: CloudDataSource,
    ) : LoadRepository {
        override suspend fun load() {
            try {
                val dataList = cloudDataSource.load()
                val questions: List<QuestionCache> =
                    dataList.mapIndexed { index, data ->
                        val incorrects: List<IncorrectCache> =
                            data.incorrectAnswers.map {
                                IncorrectCache(questionId = index, choice = it)
                            }
                        dao.saveIncorrects(incorrects)
                        QuestionCache(
                            id =  index,
                            themeId = 0,
                            question = data.question,
                            correctAnswer = data.correctAnswer
                        )
                    }
                dao.saveQuestions(questions)
            } catch (e: Exception) {
                if (e is IOException)
                    throw NoInternetConnectionException()
                if (e is IllegalStateException)
                    throw BackendError(e.message ?: "error")
                throw ServiceUnavailable()
            }
        }
    }

    class Fake(
    ) : LoadRepository {
        private var count = 0
        override suspend fun load() {
            if (count == 0) {
                delay(1000)
                count++
                throw NoInternetConnectionException()
            } else {
                delay(1000)
                count--
            }
        }
    }
}

class NoInternetConnectionException : Exception()
class BackendError(override val message: String) : Exception()
class ServiceUnavailable : Exception()
