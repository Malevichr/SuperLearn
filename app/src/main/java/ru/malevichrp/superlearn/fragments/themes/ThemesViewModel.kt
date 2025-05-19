package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync
import ru.malevichrp.superlearn.core.presentation.UiObservable
import ru.malevichrp.superlearn.views.recycler.TextItems

class ThemesViewModel(
    private val repository: ThemesRepository,
    runAsync: RunAsync,
    private val uiObservable: UiObservable<ThemesUiState>
) : MyViewModel.Async.Abstract<ThemesUiState>(
    runAsync,
    uiObservable
) {
    fun navigateToTheme(themeId: Int) {
        repository.changeTargetTheme(themeId, false)
        uiObservable.postUiState(ThemesUiState.NavigateToThemeFragment)
    }

    fun createTheme() {
        repository.changeTargetTheme(-1, true)
        uiObservable.postUiState(ThemesUiState.NavigateToThemeFragment)
    }

    fun loadThemes() {
        handleAsync {
            val themes = try {
                repository.loadThemes()
            } catch (_: Exception) {
                TextItems()
            }

            if (repository.isEdit())
                ThemesUiState.Editable(themes)
            else
                ThemesUiState.NotEditable(themes)
        }
    }
}
