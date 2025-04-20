package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.presentation.UiObservable

interface ThemesUiObservable : UiObservable<ThemesUiState> {
    class Base : UiObservable.Abstract<ThemesUiState>(), ThemesUiState
}