package ru.malevichrp.superlearn.data.learn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import ru.malevichrp.superlearn.fragments.theory.TheoryItem

@Entity("Themes")
class ThemeCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("title")
    val title: String
)

@Entity(
    "Theory", foreignKeys = [
        ForeignKey(
            entity = ThemeCache::class,
            parentColumns = ["id"],
            childColumns = ["theme_id"],
            onDelete = CASCADE
        )
    ]
)
class TheoryCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("theme_id")
    val themeId: Int,
    @ColumnInfo("content")
    val content: String = "",
    @ColumnInfo("uri")
    val uri: String = "",
    @ColumnInfo("is_image")
    val isImage: Boolean
) {
    fun toTheoryItem(): TheoryItem = TheoryItem(
        text = content,
        uri = uri,
        isImage = isImage,
        id = id
    )
}
