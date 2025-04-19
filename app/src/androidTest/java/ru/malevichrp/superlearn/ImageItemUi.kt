package ru.malevichrp.superlearn

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

interface ImageItemUi: Visible {
    fun hold()
    class Base(
        id: Int,
        containerMatcher: Matcher<View>
    ): AbstractUi(
        matcher = allOf(
            withId(id),
            withParent(containerMatcher),
            withParent(isAssignableFrom(RecyclerView::class.java))
        )
    ),ImageItemUi {
        override fun hold() {
            perform(ViewActions.longClick())
        }
    }
}
