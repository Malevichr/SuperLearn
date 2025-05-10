package ru.malevichrp.superlearn.fragments.quiz.game.presentation

import ru.malevichrp.superlearn.core.presentation.UiObservable

interface GameUiObservable : UiObservable<GameUiState> {
    class Base : UiObservable.Abstract<GameUiState>(), GameUiObservable
}