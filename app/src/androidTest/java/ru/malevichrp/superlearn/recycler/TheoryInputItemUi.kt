package ru.malevichrp.superlearn.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.malevichrp.superlearn.AbstractUi

interface TheoryInputItemUi {
    fun assertText(text: String)
    fun inputText(text: String)
    class Base(
        id: Int,
        containerMatcher: Matcher<View>,
    ) : AbstractUi(
        matcher = allOf(
            withId(id),
            withParent(containerMatcher),
            withParent(isAssignableFrom(RecyclerView::class.java))
        )
    ), TheoryInputItemUi {
        override fun assertText(text: String) {
            check(withText(text))
        }

        override fun inputText(text: String) {
            check(isDisplayed())
            perform(typeText(text))
            perform(closeSoftKeyboard())
        }
    }
}
