package ru.malevichrp.superlearn.fragments.quiz.load.data

interface LoadResult {
    fun message(): String
    fun isSuccessful(): Boolean

    data class Error(private val message: String) : LoadResult {
        override fun message(): String = message

        override fun isSuccessful(): Boolean = false

    }

    object Success : LoadResult {
        override fun message(): String {
            throw IllegalStateException("cannot happen")
        }

        override fun isSuccessful(): Boolean = true
    }
}