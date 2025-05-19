package ru.malevichrp.superlearn.fragments.quiz.game.data

import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.QuestionAndChoicesDao

interface GameRepository {
    suspend fun questionAndChoices(): QuestionAndChoices

    fun saveUserChoice(index: Int)

    suspend fun check(): CorrectAndUserChoiceIndexes

    fun next()

    fun isLastQuestion(): Boolean

    suspend fun clearProgress()


    class Base(
        private val index: IntCache,
        private val userChoiceIndex: IntCache,
        private val corrects: IntCache,
        private val incorrects: IntCache,
        private val dao: QuestionAndChoicesDao,
        private val size: Int,
        private val targetThemeId: IntCache
    ) : GameRepository {
        override suspend fun questionAndChoices(): QuestionAndChoices {
            val question = dao.getQuestionByThemeAndIndex(targetThemeId.read(), index.read())
                ?: throw LastQuestionException()
            return question.toQuestionAndChoices()
        }

        override fun saveUserChoice(index: Int) {
            userChoiceIndex.save(index)
        }

        override suspend fun check(): CorrectAndUserChoiceIndexes {
            val correctIndex = questionAndChoices().correctIndex
            val userIndex = userChoiceIndex.read()
            if (correctIndex == userIndex)
                corrects.increment()
            else
                incorrects.increment()
            return CorrectAndUserChoiceIndexes(
                correctIndex,
                userIndex
            )
        }

        override fun next() {
            index.increment()
            userChoiceIndex.save(-1)
        }

        override fun isLastQuestion(): Boolean =
            index.read() + 1 == size

        override suspend fun clearProgress() {
            index.default()
            userChoiceIndex.default()
        }
    }

    class Fake(
        private val index: IntCache,
        private val userChoiceIndex: IntCache,
        private val corrects: IntCache,
        private val incorrects: IntCache,
        private val list: List<QuestionAndChoices> = listOf(
            QuestionAndChoices(
                question = "What color is the sky?",
                listOf("blue", "green", "red", "yellow"),
                correctIndex = 0
            ),
            QuestionAndChoices(
                question = "What color is the grass?",
                listOf("green", "blue", "red", "yellow"),
                correctIndex = 0
            ),
        ),
    ) : GameRepository {

        override suspend fun questionAndChoices(): QuestionAndChoices {
            index.save(index.read() % 2)
            return list[index.read()]
        }

        override fun saveUserChoice(index: Int) {
            userChoiceIndex.save(index)
        }

        override suspend fun check(): CorrectAndUserChoiceIndexes {
            val correctIndex = questionAndChoices().correctIndex
            val userIndex = userChoiceIndex.read()
            if (correctIndex == userIndex)
                corrects.increment()
            else
                incorrects.increment()
            return CorrectAndUserChoiceIndexes(
                correctIndex,
                userIndex
            )
        }

        override fun next() {
            index.increment()
            userChoiceIndex.save(-1)
        }

        override fun isLastQuestion(): Boolean =
            index.read() + 1 == list.size

        override suspend fun clearProgress() {
            index.default()
            userChoiceIndex.default()
        }
    }
}

class LastQuestionException : IllegalStateException("Last question")