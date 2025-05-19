package ru.malevichrp.superlearn.fragments.theory

import ru.malevichrp.superlearn.core.presentation.UiObservable

interface TheoryUiObservable : UiObservable<TheoryUiState> {
    class Base : UiObservable.Abstract<TheoryUiState>(), TheoryUiObservable
}
