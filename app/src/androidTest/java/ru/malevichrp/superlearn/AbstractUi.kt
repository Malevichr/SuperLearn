package ru.malevichrp.superlearn

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

abstract class AbstractUi(
    val matcher: Matcher<View>
) : Visible, Enable, Click{
    private val interaction: ViewInteraction =
        onView(matcher)
    override fun assertVisible() {
        check(isDisplayed())
    }

    override fun assertNotVisible() {
        check(not(isDisplayed()))
    }

    override fun assertEnable() {
        check(isEnabled())
    }

    override fun assertNotEnable() {
        check(isNotEnabled())
    }

    override fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }

    protected fun check(matcher: Matcher<View>){
        interaction.check(matches(matcher))
    }
    protected fun perform(action: ViewAction){
        interaction.perform(action)
    }
}
interface Visible{
    fun assertVisible()
    fun assertNotVisible()

}
interface Enable{
    fun assertEnable()
    fun assertNotEnable()
}
interface Click{
    fun click()
}