package ru.malevichrp.superlearn.core.presentation

import ru.malevichrp.superlearn.fragments.editQuestion.EditQuestionScreen
import ru.malevichrp.superlearn.fragments.editQuestion.NavigateToEditQuestion
import ru.malevichrp.superlearn.fragments.editTest.EditTestScreen
import ru.malevichrp.superlearn.fragments.editTest.NavigateToEditTest
import ru.malevichrp.superlearn.fragments.quiz.NavigateToQuiz
import ru.malevichrp.superlearn.fragments.quiz.game.presentation.GameScreen
import ru.malevichrp.superlearn.fragments.quiz.game.presentation.NavigateToGame
import ru.malevichrp.superlearn.fragments.quiz.gameover.presentation.GameOverScreen
import ru.malevichrp.superlearn.fragments.quiz.gameover.presentation.NavigateToGameOver
import ru.malevichrp.superlearn.fragments.quiz.load.presentation.LoadScreen
import ru.malevichrp.superlearn.fragments.quiz.load.presentation.NavigateToLoad
import ru.malevichrp.superlearn.fragments.theme.NavigateToTheme
import ru.malevichrp.superlearn.fragments.theme.ThemeScreen
import ru.malevichrp.superlearn.fragments.themes.NavigateToThemes
import ru.malevichrp.superlearn.fragments.themes.ThemesScreen
import ru.malevichrp.superlearn.fragments.theory.NavigateToTheory
import ru.malevichrp.superlearn.fragments.theory.TheoryScreen

interface Navigate : NavigateToTheme, NavigateToThemes,
    NavigateToQuiz, NavigateToEditTest,
    NavigateToTheory, NavigateToEditQuestion, NavigateToGame,
    NavigateToGameOver, NavigateToLoad {
    fun navigate(screen: Screen)
    override fun navigateToTheme() {
        navigate(ThemeScreen)
    }

    override fun navigateToThemes() {
        navigate(ThemesScreen)
    }

    override fun navigateToEditTest() {
        navigate(EditTestScreen)
    }

    override fun navigateToTheory() {
        navigate(TheoryScreen)
    }

    override fun navigateToQuiz() {
        navigateToLoad()
    }

    override fun navigateToEditQuestion() {
        navigate(EditQuestionScreen)
    }

    override fun navigateToGame() {
        navigate(GameScreen)
    }

    override fun navigateToGameOver() {
        navigate(GameOverScreen)
    }

    override fun navigateToLoad() {
        navigate(LoadScreen)
    }
}