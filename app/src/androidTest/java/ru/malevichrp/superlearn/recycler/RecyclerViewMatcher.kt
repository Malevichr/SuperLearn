package ru.malevichrp.superlearn.recycler


import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

interface RecyclerViewMatcher {

    fun atPosition(position: Int, targetViewId: Int = -1): Matcher<View>
    fun hasItemCount(itemCount: Int): Matcher<View>
    fun hasItemAtPosition(position: Int, matcher: Matcher<View>): Matcher<View>

    class Base(@IdRes private val recyclerViewId: Int) : RecyclerViewMatcher {

        override fun atPosition(position: Int, targetViewId: Int) =
            object : TypeSafeMatcher<View>() {

                var resources: Resources? = null
                var childView: View? = null

                override fun describeTo(description: Description) {
                    var idDescription = recyclerViewId.toString()
                    if (this.resources != null) {
                        idDescription = try {
                            this.resources!!.getResourceName(recyclerViewId)
                        } catch (e: Resources.NotFoundException) {
                            String.format("%s (resource name not found)", recyclerViewId)
                        }
                    }

                    description.appendText("RecyclerView with id: $idDescription at position: $position")
                }

                override fun matchesSafely(view: View): Boolean {
                    this.resources = view.resources

                    if (childView == null) {
                        val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                        if (recyclerView.id == recyclerViewId) {
                            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                            if (viewHolder != null) {
                                childView = viewHolder.itemView
                            }
                        } else {
                            return false
                        }
                    }

                    return if (targetViewId == -1) {
                        view === childView
                    } else {
                        val targetView = childView!!.findViewById<View>(targetViewId)
                        view === targetView
                    }
                }
            }

        override fun hasItemCount(itemCount: Int): Matcher<View> =
            object : BoundedMatcher<View, RecyclerView>(
                RecyclerView::class.java
            ) {

                override fun describeTo(description: Description) {
                    description.appendText("has $itemCount items")
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    return view.adapter?.itemCount == itemCount
                }
            }

        override fun hasItemAtPosition(position: Int, matcher: Matcher<View>): Matcher<View> =
            object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

                override fun describeTo(description: Description?) {
                    description?.appendText("has item at position $position : ")
                    matcher.describeTo(description)
                }

                override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                        ?: return false
                    return matcher.matches(viewHolder.itemView)
                }
            }
    }
}