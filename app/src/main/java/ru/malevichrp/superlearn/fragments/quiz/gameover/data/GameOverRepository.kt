package ru.malevichrp.superlearn.fragments.quiz.gameover.data

import ru.malevichrp.superlearn.core.data.IntCache

interface GameOverRepository {
    fun stats(): Pair<Int, Int>
    fun clearStats()
    class Base(
        private val corrects: IntCache,
        private val incorrects: IntCache
    ) : GameOverRepository {
        override fun stats(): Pair<Int, Int> {
            val statsPair = Pair(corrects.read(), incorrects.read())
            return statsPair
        }
        override fun clearStats() {
            corrects.default()
            incorrects.default()
        }
    }
}