package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.LearnDao
import ru.malevichrp.superlearn.views.recycler.TextItems

interface ThemesRepository {
    fun changeTargetTheme(themeId: Int, isNew: Boolean)
    fun isEdit(): Boolean
    suspend fun loadThemes(): TextItems
    class Fake(
        private val targetIsNew: BooleanCache,
        private val targetThemeText: IntCache
    ) : ThemesRepository {
        override fun changeTargetTheme(themeId: Int, isNew: Boolean) {
            targetThemeText.save(themeId)
            targetIsNew.save(isNew)
        }

        override fun isEdit(): Boolean = true

        override suspend fun loadThemes(): TextItems {
            return ThemesList.textItems
        }
    }
    class Base(
        private val targetIsNew: BooleanCache,
        private val targetThemeId: IntCache,
        private val learnDao: LearnDao
    ) : ThemesRepository{
        override fun changeTargetTheme(themeId: Int, isNew: Boolean) {
            targetThemeId.save(themeId)
            targetIsNew.save(isNew)
        }

        override fun isEdit(): Boolean = true

        override suspend fun loadThemes(): TextItems {
            val themes = learnDao.themes()
            val textItems = TextItems()
            themes.forEach {
                textItems.textTitles.add(it.title)
                textItems.itemIds.add(it.id)
            }
            return textItems
        }
    }
}

object ThemesList {
    val textItems = TextItems()
        .add("first")
        .add("second")
        .add("third")
}