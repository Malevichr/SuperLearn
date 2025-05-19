package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.presentation.UiState
import ru.malevichrp.superlearn.fragments.theme.NavigateToTheme
import ru.malevichrp.superlearn.views.recycler.TextAdapter
import ru.malevichrp.superlearn.views.recycler.TextItems
import ru.malevichrp.superlearn.views.visibilitybutton.VisibilityButton
import ru.malevichrp.superlearn.views.visibilitybutton.VisibilityUiState

interface ThemesUiState : UiState{
    fun update(addThemeButton: VisibilityButton, textAdapter: TextAdapter) = Unit
    fun navigate(navigate: NavigateToTheme) = Unit

    data class Editable(private val items: TextItems): ThemesUiState{
        override fun update(addThemeButton: VisibilityButton, textAdapter: TextAdapter) {
            addThemeButton.update(VisibilityUiState.Visible)
            textAdapter.addAll(items)
        }
    }
    data class NotEditable(private val items: TextItems): ThemesUiState{
        override fun update(addThemeButton: VisibilityButton, textAdapter: TextAdapter) {
            addThemeButton.update(VisibilityUiState.Gone)
            textAdapter.addAll(items)
        }
    }
    data object NavigateToThemeFragment: ThemesUiState{
        override fun navigate(navigate: NavigateToTheme) {
            navigate.navigateToTheme()
        }
    }
}
