package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.presentation.UiState
import ru.malevichrp.superlearn.fragments.theme.NavigateToTheme
import ru.malevichrp.superlearn.views.recycler.TextAdapter
import ru.malevichrp.superlearn.views.visibilitybutton.VisibilityButton
import ru.malevichrp.superlearn.views.visibilitybutton.VisibilityUiState

interface ThemesUiState : UiState{
    fun update(addThemeButton: VisibilityButton, textAdapter: TextAdapter) = Unit
    fun navigate(navigate: NavigateToTheme) = Unit

    data class Editable(private val arrayList: ArrayList<CharSequence>): ThemesUiState{
        override fun update(addThemeButton: VisibilityButton, textAdapter: TextAdapter) {
            addThemeButton.update(VisibilityUiState.Visible)
            textAdapter.addAll(arrayList)
        }
    }
    data class NotEditable(private val arrayList: ArrayList<CharSequence>): ThemesUiState{
        override fun update(addThemeButton: VisibilityButton, textAdapter: TextAdapter) {
            addThemeButton.update(VisibilityUiState.Gone)
            textAdapter.addAll(arrayList)
        }
    }
    data object NavigateToThemeFragment: ThemesUiState{
        override fun navigate(navigate: NavigateToTheme) {
            navigate.navigateToTheme()
        }
    }
}
