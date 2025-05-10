package ru.malevichrp.superlearn.fragments.quiz.game.presentation

import ru.malevichrp.superlearn.core.presentation.Screen

object GameScreen : Screen.ReplaceWithBackStack(GameFragment::class.java, "GameScreen")