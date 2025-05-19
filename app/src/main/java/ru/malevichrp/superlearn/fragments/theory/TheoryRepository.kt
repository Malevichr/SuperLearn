package ru.malevichrp.superlearn.fragments.theory

import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.TheoryCache
import ru.malevichrp.superlearn.data.learn.TheoryDao

interface TheoryRepository {
    suspend fun addImageAndText(uri: String): List<TheoryItem>
    suspend fun deleteImage(imageId: Int): List<TheoryItem>
    suspend fun items(): List<TheoryItem>
    suspend fun changeInputText(id: Int, text: String)

    class Base(
        private val targetThemeId: IntCache,
        private val theoryDao: TheoryDao
    ) : TheoryRepository {
        override suspend fun addImageAndText(uri: String): List<TheoryItem> {
            theoryDao.save(
                TheoryCache(
                    themeId = targetThemeId.read(),
                    uri = uri,
                    isImage = true
                )
            )
            theoryDao.save(
                TheoryCache(
                    themeId = targetThemeId.read(),
                    content = "",
                    isImage = false
                )
            )

            val items: List<TheoryCache> = theoryDao.theoryItemsByTheme(targetThemeId.read())

            return items.map { it.toTheoryItem() }
        }

        override suspend fun deleteImage(imageId: Int): List<TheoryItem> {

            theoryDao.mergeAndDelete(targetThemeId.read(), imageId)
            val items: List<TheoryCache> = theoryDao.theoryItemsByTheme(targetThemeId.read())
            return items.map { it.toTheoryItem() }
        }

        override suspend fun items(): List<TheoryItem> {

            val items: List<TheoryCache> = theoryDao.theoryItemsByTheme(targetThemeId.read())
            val result = if (items.isEmpty()) {
                val theoryCache = TheoryCache(
                    themeId = targetThemeId.read(),
                    content = "",
                    isImage = false
                )
                theoryDao.save(theoryCache)
                listOf(theoryCache.toTheoryItem())
            } else
                items.map { it.toTheoryItem() }

            return result
        }

        override suspend fun changeInputText(id: Int, text: String) {
            theoryDao.updateContent(
                targetThemeId.read(),
                id,
                text
            )
        }
    }
}
