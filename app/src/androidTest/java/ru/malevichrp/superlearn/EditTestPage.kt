package ru.malevichrp.superlearn

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf
import ru.malevichrp.superlearn.recycler.RecyclerUi
import ru.malevichrp.superlearn.recycler.TextItemUi

class EditTestPage {
    private val containerMatcher = withParent(
        allOf(
            withId(R.id.editTestContainer),
            isAssignableFrom(LinearLayout::class.java)
        )
    )

    private val recyclerUi: RecyclerUi = RecyclerUi.Base(
        R.id.testsRecycler,
        containerMatcher
    )
    private val addQuestionButton: ButtonUi = ButtonUi.Base(
        R.id.addQuestionButton,
        R.string.add_question,
        containerMatcher
    )
    private val backButton: ButtonUi = ButtonUi.Base(
        id = R.id.backButton,
        text = R.string.back,
        containerMatcher = containerMatcher
    )

    private val questionItem: TextItemUi = TextItemUi.Base(
        R.id.text_item,
        containerMatcher,
        recyclerUi.recyclerViewMatcher(),
        0
    )

    fun assertNoQuestions() {
        recyclerUi.assertCount(0)
    }

    fun clickAddQuestion() {
        addQuestionButton.click()
    }

    fun assertOnlyTwoQuestions() {
        recyclerUi.assertCount(0)
    }

    fun clickFirstQuestions() {
        questionItem.click()
    }

    fun assertOnlyOneQuestion() {
        recyclerUi.assertCount(1)
    }


}
