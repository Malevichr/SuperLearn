package ru.malevichrp.superlearn.core.presentation

import ru.malevichrp.superlearn.fragments.editQuestion.EditQuestionScreen
import ru.malevichrp.superlearn.fragments.editQuestion.NavigateToEditQuestion
import ru.malevichrp.superlearn.fragments.editTest.EditTestScreen
import ru.malevichrp.superlearn.fragments.editTest.NavigateToEditTest
import ru.malevichrp.superlearn.fragments.quiz.NavigateToQuiz
import ru.malevichrp.superlearn.fragments.theme.NavigateToTheme
import ru.malevichrp.superlearn.fragments.theme.ThemeScreen
import ru.malevichrp.superlearn.fragments.themes.NavigateToThemes
import ru.malevichrp.superlearn.fragments.themes.ThemesScreen
import ru.malevichrp.superlearn.fragments.theory.NavigateToTheory
import ru.malevichrp.superlearn.fragments.theory.TheoryScreen

interface Navigate : NavigateToTheme, NavigateToThemes,
    NavigateToQuiz, NavigateToEditTest,
    NavigateToTheory, NavigateToEditQuestion {
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

    }

    override fun navigateToEditQuestion() {
        navigate(EditQuestionScreen)
    }
}