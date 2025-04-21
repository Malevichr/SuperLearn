package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.StringCache

interface ThemesRepository {
    fun changeTargetTheme(themeText: String, isNew: Boolean)
    fun isEdit(): Boolean
    suspend fun loadThemes() : ArrayList<CharSequence>
    class Fake(
        private val targetIsNew: BooleanCache,
        private val targetThemeText: StringCache
    ): ThemesRepository{
        override fun changeTargetTheme(themeText: String, isNew: Boolean) {
            targetThemeText.save(themeText)
            targetIsNew.save(isNew)
        }

        override fun isEdit(): Boolean = true

        override suspend fun loadThemes(): ArrayList<CharSequence> {
            return ThemesList.list
        }
    }
}
object ThemesList{
    val list = arrayListOf<CharSequence>("first", "second", "third")
}