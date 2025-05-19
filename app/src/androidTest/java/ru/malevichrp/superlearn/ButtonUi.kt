package ru.malevichrp.superlearn

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

interface ButtonUi : Visible, Click {
    class Base(
        id: Int,
        text: Int,
        containerMatcher: Matcher<View>
    ) : AbstractUi(
        matcher = allOf(
            withId(id),
            withText(text),
            containerMatcher
        )
    ), ButtonUi
}

interface RadioButtonUi : Visible, Click {
    fun assertChecked(isChecked: Boolean)
    class Base(
        id: Int,
        containerMatcher: Matcher<View>
    ) : AbstractUi(
        matcher = allOf(
            withId(id),
            containerMatcher
        )
    ), RadioButtonUi {
        override fun assertChecked(isChecked: Boolean) {
            check(
                if (isChecked)
                    isChecked()
                else
                    isNotChecked()
            )
        }
    }
}


