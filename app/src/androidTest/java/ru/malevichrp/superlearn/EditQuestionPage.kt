package ru.malevichrp.superlearn

import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf

class EditQuestionPage {
    private val containerMatcher = withParent(
        allOf(
            withId(R.id.editQuestionContainer),
            isAssignableFrom(LinearLayout::class.java)
        )
    )
    private val radioGroupContainerMatcher = withParent(
        allOf(
            isAssignableFrom(RadioGroup::class.java),
            withId(R.id.radioGroup),
            withParent(containerMatcher)
        )
    )
    private val inputQuestion: TextInputUi = TextInputUi.Base(
        R.id.editQuestionInput,
        containerMatcher
    )
    private val firstAnswer: InputAndRadioUi = InputAndRadioUi.Base(
        inputId = R.id.firstAnswerInput,
        radioButtonId = R.id.radio1,
        radioGroupContainerMatcher = radioGroupContainerMatcher
    )
    private val secondAnswer: InputAndRadioUi = InputAndRadioUi.Base(
        inputId = R.id.secondAnswerInput,
        radioButtonId = R.id.radio2,
        radioGroupContainerMatcher = radioGroupContainerMatcher
    )
    private val thirdAnswer: InputAndRadioUi = InputAndRadioUi.Base(
        inputId = R.id.thirdAnswerInput,
        radioButtonId = R.id.radio3,
        radioGroupContainerMatcher = radioGroupContainerMatcher
    )
    private val fourthAnswer: InputAndRadioUi = InputAndRadioUi.Base(
        inputId = R.id.fourthAnswerInput,
        radioButtonId = R.id.radio4,
        radioGroupContainerMatcher = radioGroupContainerMatcher
    )

    private val saveButton: ButtonUi = ButtonUi.Base(
        R.id.saveButton,
        R.string.save_and_add_new_question,
        containerMatcher
    )
    private val backButton: ButtonUi = ButtonUi.Base(
        R.id.backButton,
        R.string.back,
        withParent(containerMatcher)
    )
    private val deleteButton: ButtonUi = ButtonUi.Base(
        R.id.deleteButton,
        R.string.delete_question,
        withParent(containerMatcher)
    )

    fun assertWithQuestion(testQuestion: TestQuestions) {
        testQuestion.assertQuestion(
            inputQuestion,
            firstAnswer,
            secondAnswer,
            thirdAnswer,
            fourthAnswer
        )
    }

    fun enterQuestion(testQuestion: TestQuestions) {
        testQuestion.enterQuestion(
            inputQuestion,
            firstAnswer,
            secondAnswer,
            thirdAnswer,
            fourthAnswer
        )
    }

    fun clickSaveAndAdd() {
        saveButton.click()
    }

    fun clickBack() {
        backButton.click()
    }

    fun clickDelete() {
        deleteButton.click()
    }
}
