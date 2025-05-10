package ru.malevichrp.superlearn.fragments.editTest

import ru.malevichrp.superlearn.core.presentation.UiObservable

interface EditTestUiObservable : UiObservable<EditTestUiState> {
    class Base : UiObservable.Abstract<EditTestUiState>(), EditTestUiObservable
}