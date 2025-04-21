package ru.malevichrp.superlearn.fragments.quiz.load.data

import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.ClearDatabase
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.CloudDataSource
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.IncorrectCache
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.QuestionAndChoicesDao
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.QuestionCache
import kotlinx.coroutines.delay
import okio.IOException

interface LoadRepository {

    suspend fun load()

    class Base(
        private val dao: QuestionAndChoicesDao,
        private val cloudDataSource: CloudDataSource,
        private val clearDatabase: ClearDatabase
    ) : LoadRepository {
        override suspend fun load() {
            try {
                val dataList = cloudDataSource.load()
                clearDatabase.clear()
                val questions: List<QuestionCache> =
                    dataList.mapIndexed { index, data ->
                        val incorrects: List<IncorrectCache> =
                            data.incorrectAnswers.map {
                                IncorrectCache(questionId = index, choice = it)
                            }
                        dao.saveIncorrects(incorrects)
                        QuestionCache(index, data.question, data.correctAnswer)
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
