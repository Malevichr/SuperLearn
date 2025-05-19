package ru.malevichrp.superlearn.data.learn

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface TheoryDao {
    @Query("SELECT * FROM Theory WHERE theme_id = :id")
    suspend fun theoryItemsByTheme(id: Int): List<TheoryCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(theoryCache: TheoryCache)

    // Найти предыдущий и следующий id в рамках одной темы
    @Query(
        """
        SELECT
            (SELECT id FROM Theory WHERE theme_id = :themeId AND id < :id0 ORDER BY id DESC LIMIT 1) AS prevId,
            (SELECT id FROM Theory WHERE theme_id = :themeId AND id > :id0 ORDER BY id ASC LIMIT 1) AS nextId
    """
    )
    suspend fun getNeighborIds(themeId: Int, id0: Int): NeighborIds

    // Обновить контент записи с проверкой темы
    @Query("UPDATE Theory SET content = :newContent WHERE id = :id AND theme_id = :themeId")
    suspend fun updateContent(themeId: Int, id: Int, newContent: String)

    // Удалить запись с проверкой темы
    @Query("DELETE FROM Theory WHERE id = :id AND theme_id = :themeId")
    suspend fun delete(themeId: Int, id: Int)

    @Transaction
    suspend fun mergeAndDelete(themeId: Int, id0: Int) {
        val neighbors = getNeighborIds(themeId, id0)
        val prevId = neighbors.prevId
        val nextId = neighbors.nextId

        if (prevId != null && nextId != null) {
            val prevContent = getContentById(themeId, prevId)
            val nextContent = getContentById(themeId, nextId)

            val mergedContent =
                "$prevContent${if (nextContent.isNotEmpty()) "\n" + nextContent else ""}"

            updateContent(themeId, prevId, mergedContent)

            delete(themeId, id0)
            delete(themeId, nextId)
        }
    }

    @Query("SELECT content FROM Theory WHERE id = :id AND theme_id = :themeId")
    suspend fun getContentById(themeId: Int, id: Int): String
}

data class NeighborIds(val prevId: Int?, val nextId: Int?)
