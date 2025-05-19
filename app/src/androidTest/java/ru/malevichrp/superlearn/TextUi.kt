package ru.malevichrp.superlearn

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

interface TextUi : Visible {
    fun waitTillVisible()
    fun waitTillNotExist()
    class Base(
        private val id: Int,
        text: Int,
        containerMatcher: Matcher<View>
    ) : AbstractUi(
        allOf(
            withId(id),
            withText(text),
            containerMatcher
        )
    ), TextUi {
        override fun waitTillVisible() {
            onView(isRoot()).perform(waitTillDisplayed(id, 4000))
        }

        override fun waitTillNotExist() {
            onView(isRoot()).perform(waitTillDoesNotExist(id, 4000))
        }
    }
}