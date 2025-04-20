package ru.malevichrp.superlearn.core.presentation

import ru.malevichrp.superlearn.fragments.theme.NavigateToTheme
import ru.malevichrp.superlearn.fragments.theme.ThemeScreen
import ru.malevichrp.superlearn.fragments.themes.NavigateToThemes
import ru.malevichrp.superlearn.fragments.themes.ThemesScreen

interface Navigate: NavigateToTheme, NavigateToThemes{
    fun navigate(screen: Screen)
    override fun navigateToTheme() {
        navigate(ThemeScreen)
    }

    override fun navigateToThemes() {
        navigate(ThemesScreen)
    }
}