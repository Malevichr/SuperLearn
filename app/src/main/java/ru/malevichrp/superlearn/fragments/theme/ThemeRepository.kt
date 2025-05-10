package ru.malevichrp.superlearn.fragments.theme

import android.util.Log
import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.LearnDao
import ru.malevichrp.superlearn.data.learn.ThemeCache
import ru.malevichrp.superlearn.fragments.themes.ThemesList

interface ThemeRepository {
    suspend fun changeThemeName(text: String)
    suspend fun deleteTargetTheme()
    fun isEdit(): Boolean
    suspend fun initTheme(): String
    class Fake(
        private val targetIsNew: BooleanCache,
        private val targetThemeId: IntCache,
        private val isEdit: BooleanCache
    ) : ThemeRepository {
        override suspend fun changeThemeName(text: String) {
            ThemesList.textItems.remove(targetThemeId.read())
            ThemesList.textItems.add(text)
            targetThemeId.save(ThemesList.textItems.lastAddedId())
        }

        override suspend fun deleteTargetTheme() {
            ThemesList.textItems.remove(targetThemeId.read())
        }


        override fun isEdit(): Boolean =
            isEdit.read()

        override suspend fun initTheme(): String {
            if (targetIsNew.read()) {
                ThemesList.textItems.add("New Theme")
                targetThemeId.save(ThemesList.textItems.lastAddedId())
                targetIsNew.save(false)
                return "New Theme"
            }
            return "themeText()"
        }
    }

    class Base(
        private val targetIsNew: BooleanCache,
        private val targetThemeId: IntCache,
        private val isEdit: BooleanCache,
        private val learnDao: LearnDao
    ) : ThemeRepository {
        override suspend fun changeThemeName(text: String) {
            learnDao.updateTheme(
                ThemeCache(
                    targetThemeId.read(),
                    text
                )
            )
        }

        override suspend fun deleteTargetTheme() {
            learnDao.deleteById(targetThemeId.read())
        }

        override fun isEdit(): Boolean =
            isEdit.read()

        override suspend fun initTheme(): String {
            if (targetIsNew.read()) {
                val newTheme = "New Theme"
                val id = learnDao.insertTheme(
                    ThemeCache(title = newTheme)
                )
                targetThemeId.save(id.toInt())
                targetIsNew.save(false)
                return newTheme
            } else {
                Log.d("mlvc", targetThemeId.read().toString())
                var title = ""
                try {
                     title = learnDao.themeById(targetThemeId.read()).title
                } catch (_: Exception) {
                    title = "exception"
                }
                return title
            }

        }
    }
}
