package ru.malevichrp.superlearn.recycler

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.malevichrp.superlearn.AbstractUi
import ru.malevichrp.superlearn.Visible

interface RecyclerUi : Visible {
    fun assertIdle()
    fun assertCount(count: Int)
    fun asserAtPositionsMatches(position: Int, matcher: Matcher<View>)
    fun recyclerViewMatcher(): RecyclerViewMatcher
    class Base(
        id: Int,
        containerMatcher: Matcher<View>
    ) : AbstractUi(
            allOf(
                withId(id),
                containerMatcher
            )
    ), RecyclerUi {
        private val recyclerViewMatcher = RecyclerViewMatcher.Base(id)

        override fun recyclerViewMatcher(): RecyclerViewMatcher = recyclerViewMatcher

        override fun assertIdle() {
            assertVisible()
            assertCount(0)
        }

        override fun assertCount(count: Int) {
            check(recyclerViewMatcher.hasItemCount(count))
        }

        override fun asserAtPositionsMatches(position: Int, matcher: Matcher<View>) {
            check(recyclerViewMatcher.hasItemAtPosition(
                position,
                matcher
            ))
        }
    }
}
