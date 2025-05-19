package ru.malevichrp.superlearn

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf

class EditThemePage {
    private val containerMatcher = withParent(
        allOf(
            withId(R.id.themeContainer),
            isAssignableFrom(LinearLayout::class.java)
        )
    )

    private val textInputUi: TextInputUi = TextInputUi.Base(
        id = R.id.inputEditText,
        containerMatcher = containerMatcher
    )

    private val backButton: ButtonUi = ButtonUi.Base(
        id = R.id.backButton,
        text = R.string.back,
        containerMatcher = containerMatcher
    )
    private val theoryButton: ButtonUi = ButtonUi.Base(
        id = R.id.theoryButton,
        text = R.string.test,
        containerMatcher = withParent(containerMatcher)
    )
    private val testButton: ButtonUi = ButtonUi.Base(
        id = R.id.testButton,
        text = R.string.test,
        containerMatcher = withParent(containerMatcher)
    )

    fun renameTitle(title: String) {
        textInputUi.replaceInputText(title)
    }

    fun clickBack() {
        backButton.click()
    }

    fun assertWithTitle(title: String) {
        textInputUi.assertInputText(title)
    }

    fun clickTheory() {
        theoryButton.click()
    }

    fun clickTest() {
        testButton.click()
    }
}
