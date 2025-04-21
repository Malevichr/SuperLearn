package ru.malevichrp.superlearn.fragments.theme

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.StringCache
import ru.malevichrp.superlearn.fragments.themes.ThemesList

interface ThemeRepository {
    fun changeThemeName(text: String)
    fun deleteTargetTheme()
    fun themeText(): String
    fun isEdit(): Boolean
    fun createNewTheme()
    class Fake(
        private val targetIsNew: BooleanCache,
        private val targetThemeText: StringCache,
        private val isEdit: BooleanCache
    ) : ThemeRepository {
        override fun changeThemeName(text: String) {
            ThemesList.list.remove(targetThemeText.read())
            ThemesList.list.add(text)
            targetThemeText.save(text)
        }

        override fun deleteTargetTheme() {
            ThemesList.list.remove(themeText())
        }

        override fun themeText(): String =
            targetThemeText.read()

        override fun isEdit(): Boolean =
            isEdit.read()

        override fun createNewTheme() {
            if (targetIsNew.read())
                ThemesList.list.add(themeText())
        }
    }
}
