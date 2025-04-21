package ru.malevichrp.superlearn.fragments.quiz.game.data

import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.ClearDatabase
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.QuestionAndChoicesDao
import ru.malevichrp.superlearn.core.data.IntCache

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
        private val clearDatabase: ClearDatabase,
        private val size: Int
    ) : GameRepository {
        override suspend fun questionAndChoices(): QuestionAndChoices {
            index.save(index.read() % size)
            val id = index.read()
            val question = dao.question(id)
            val incorrects = dao.incorects(id)
            val choices = (listOf(question.correctAnswer) + incorrects.map { it.choice }).shuffled()

            return QuestionAndChoices(
                question.question,
                choices,
                choices.indexOf(question.correctAnswer)
            )
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
            index.save((index.read() + 1) % size)
            userChoiceIndex.save(-1)
        }

        override fun isLastQuestion(): Boolean =
            index.read() + 1 == size

        override suspend fun clearProgress() {
            index.default()
            userChoiceIndex.default()
            clearDatabase.clear()
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
            index.save((index.read() + 1) % list.size)
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

