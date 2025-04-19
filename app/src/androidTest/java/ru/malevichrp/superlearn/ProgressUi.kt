package ru.malevichrp.superlearn

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

interface ProgressUi : Visible {
    class Base(containerMatcher: Matcher<View>) : AbstractUi(
        allOf(
            withId(R.id.progressBar),
            isAssignableFrom(ProgressBar::class.java),
            containerMatcher
        )
    ), ProgressUi
}

