package ru.malevichrp.superlearn

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import ru.malevichrp.superlearn.recycler.RecyclerUi
import ru.malevichrp.superlearn.recycler.TextItemUi

class EditThemesPage {
    private val containerMatcher = withParent(
        allOf(
            isAssignableFrom(LinearLayout::class.java),
            withId(R.id.themeContainer)
        )
    )
    private val nameTextUi: TextUi = TextUi.Base(
        id = R.id.fullNameText,
        text = R.string.name,
        containerMatcher = containerMatcher
    )
    private val recyclerUi = RecyclerUi.Base(
        id = R.id.themesRecycler,
        containerMatcher = containerMatcher
    )
    private val textItemUi = TextItemUi.Base(
        R.id.text_item,
        containerMatcher,
        recyclerViewMatcher = recyclerUi.recyclerViewMatcher(),
        position = 0
    )
    private val addThemeButtonUi: ButtonUi = ButtonUi.Base(
        id = R.id.addThemeButton,
        text = R.string.add_theme,
        containerMatcher = containerMatcher
    )

    fun assertNoThemes() {
        nameTextUi.assertVisible()
        recyclerUi.assertCount(0)
    }

    fun clickAddTheme() {
        addThemeButtonUi.click()
    }

    fun assertFirstThemeWithTitle(title: String) {
        recyclerUi.asserAtPositionsMatches(0, withText(title))
    }


    fun clickThemeWithText(title: String) {
        textItemUi.assertWithText(title)
        textItemUi.click()
    }
}
