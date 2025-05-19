package ru.malevichrp.superlearn.fragments.quiz.load.data

import kotlinx.coroutines.delay
import okio.IOException
import ru.malevichrp.superlearn.data.learn.QuestionAndChoicesDao
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.CloudDataSource

interface LoadRepository {

    suspend fun load()

    class Base(
        private val dao: QuestionAndChoicesDao,
        private val cloudDataSource: CloudDataSource,
    ) : LoadRepository {
        override suspend fun load() {
            try {

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
