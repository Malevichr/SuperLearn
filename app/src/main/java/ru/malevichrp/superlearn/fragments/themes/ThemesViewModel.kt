package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync
import ru.malevichrp.superlearn.core.presentation.UiObservable

class ThemesViewModel(
    private val repository: ThemesRepository,
    runAsync: RunAsync,
    private val uiObservable: UiObservable<ThemesUiState>
) : MyViewModel.Async.Abstract<ThemesUiState>(
    runAsync,
    uiObservable
) {
    fun navigateToTheme(themeText: String) {
        repository.changeTargetTheme(themeText, false)
        uiObservable.postUiState(ThemesUiState.NavigateToThemeFragment)
    }

    fun createTheme() {
        repository.changeTargetTheme("New Theme", true)
        uiObservable.postUiState(ThemesUiState.NavigateToThemeFragment)
    }
    private var processDeath: Boolean = true
    private val themes: ArrayList<CharSequence> = arrayListOf()
    fun loadThemes(isFirstRun: Boolean = true) {
        handleAsync {
            if (isFirstRun or processDeath) {
                processDeath = false
                themes.clear()
                themes.addAll(
                    try {
                        repository.loadThemes()
                    } catch (_: Exception) {
                        arrayListOf()
                    }
                )
            }
            if (repository.isEdit())
                ThemesUiState.Editable(themes)
            else
                ThemesUiState.NotEditable(themes)
        }
    }
}
