package ru.malevichrp.superlearn.fragments.quiz.load.presentation

import ru.malevichrp.superlearn.core.presentation.UiObservable

interface LoadUiObservable : UiObservable<LoadUiState> {

    class Base : UiObservable.Abstract<LoadUiState>(), LoadUiObservable
}