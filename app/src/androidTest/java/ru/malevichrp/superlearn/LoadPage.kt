package ru.malevichrp.superlearn

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf

class LoadPage {
    private val containerMatcher = withParent(
        allOf(
            isAssignableFrom(LinearLayout::class.java),
            withId(R.id.loadContainer)
        )
    )
    private val progress: ProgressUi = ProgressUi.Base(
        containerMatcher = containerMatcher
    )
    private val retryButton: ButtonUi = ButtonUi.Base(
        id = R.id.retryButton,
        text = R.string.retry,
        containerMatcher = containerMatcher
    )
    private val errorText:TextUi = TextUi.Base(
        id = R.id.errorTextView,
        text = R.string.error,
        containerMatcher = containerMatcher
    )

    fun assertProgressState() {
        progress.assertVisible()
        retryButton.assertNotVisible()
        errorText.assertNotVisible()
    }

    fun assertErrorState() {
        progress.assertNotVisible()
        retryButton.assertVisible()
        errorText.assertVisible()
    }

    fun waitTillError() {
        errorText.waitTillVisible()
    }

    fun waitTillGone() {
        errorText.waitTillNotExist()
    }

    fun clickRetry() {
        retryButton.click()
    }
}