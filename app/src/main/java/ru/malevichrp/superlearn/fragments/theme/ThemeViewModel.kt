package ru.malevichrp.superlearn.fragments.theme

import ru.malevichrp.superlearn.core.presentation.MyViewModel

class ThemeViewModel(
    private val repository: ThemeRepository
) : MyViewModel {
    fun changeThemeName(text: String) {
        repository.changeThemeName(text)
    }

    fun deleteCurrentTheme() {
        repository.deleteTargetTheme()
    }

    fun themeText(): String =
        repository.themeText()


    fun isEdit(): Boolean =
        repository.isEdit()

    fun createNewTheme() {
        repository.createNewTheme()
    }
}
