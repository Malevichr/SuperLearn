package ru.malevichrp.superlearn

import android.view.View
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

interface TextInputUi {
    fun replaceInputText(text: String)
    fun assertInputText(text: String)
    class Base(
        id: Int,
        containerMatcher: Matcher<View>
    ) : AbstractUi(
        allOf(
            withParent(
                withParent(containerMatcher)
            ),
            withId(id)
        )
    ), TextInputUi {
        override fun replaceInputText(text: String) {
            perform(ViewActions.replaceText(text))
        }

        override fun assertInputText(text: String) {
            check(withText(text))
        }
    }
}
