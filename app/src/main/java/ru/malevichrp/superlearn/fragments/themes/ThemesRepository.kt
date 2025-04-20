package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.StringCache

interface ThemesRepository {
    fun changeTargetTheme(themeText: String, isNew: Boolean)
    fun isEdit(): Boolean
    suspend fun loadThemes() : ArrayList<CharSequence>
    class Base(
        private val targetIsNew: BooleanCache,
        private val targetThemeText: StringCache
    ): ThemesRepository{
        override fun changeTargetTheme(themeText: String, isNew: Boolean) {
            targetThemeText.save(themeText)
            targetIsNew.save(isNew)
        }

        override fun isEdit(): Boolean = true

        override suspend fun loadThemes(): ArrayList<CharSequence> {
            return arrayListOf("first", "second", "third")
        }
    }
}
