package ru.malevichrp.superlearn.recycler

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import ru.malevichrp.superlearn.AbstractUi
import ru.malevichrp.superlearn.Click
import ru.malevichrp.superlearn.Visible

interface TextItemUi : Visible, Click {
    fun assertWithText(text: String)
    class Base(
        id: Int,
        containerMatcher: Matcher<View>,
        recyclerViewMatcher: RecyclerViewMatcher,
        position: Int
    ) : AbstractUi(
        matcher = allOf(
            withId(id),
            withParent(containerMatcher),
            isDescendantOfA(recyclerViewMatcher.atPosition(position))
        )

    ), TextItemUi {
        override fun assertWithText(text: String) {
            check(withText(text))
        }
    }
}
