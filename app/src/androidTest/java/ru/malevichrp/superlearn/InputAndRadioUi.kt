package ru.malevichrp.superlearn

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

interface InputAndRadioUi {
    fun assertTextAndChecked(text: String, isChecked: Boolean)
    fun enterTextAndChecked(text: String, isChecked: Boolean)
    class Base(
        inputId: Int,
        radioButtonId: Int,
        radioGroupContainerMatcher: Matcher<View>
    ): InputAndRadioUi{
        private val containerMatcher = allOf(
            isAssignableFrom(LinearLayout::class.java),
            radioGroupContainerMatcher
        )
        private val input: TextInputUi = TextInputUi.Base(
            inputId,
            containerMatcher
        )
        private val radioButton: RadioButtonUi = RadioButtonUi.Base(
            radioButtonId,
            containerMatcher
        )

        override fun assertTextAndChecked(text: String, isChecked: Boolean) {
            input.assertInputText(text)
            radioButton.assertChecked(isChecked)
        }

        override fun enterTextAndChecked(text: String, isChecked: Boolean) {
            input.replaceInputText(text)
            if(isChecked)
                radioButton.click()
        }
    }
}
