package ru.malevichrp.superlearn.fragments.theme

import ru.malevichrp.superlearn.core.presentation.Screen

object ThemeScreen : Screen.ReplaceWithBackStack(
    ThemeFragment::class.java,
    "ThemeScreen"
)