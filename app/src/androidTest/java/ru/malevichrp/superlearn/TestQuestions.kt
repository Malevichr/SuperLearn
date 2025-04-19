package ru.malevichrp.superlearn

interface TestQuestions {
    fun enterQuestion(
        questionInputUi: TextInputUi,
        answer1Ui: InputAndRadioUi,
        answer2Ui: InputAndRadioUi,
        answer3Ui: InputAndRadioUi,
        answer4Ui: InputAndRadioUi
    )
    fun assertQuestion(
        questionInputUi: TextInputUi,
        answer1Ui: InputAndRadioUi,
        answer2Ui: InputAndRadioUi,
        answer3Ui: InputAndRadioUi,
        answer4Ui: InputAndRadioUi
    )
    abstract class Abstract(
        val questionText: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val answer4: String,
        val correctAnswer: Int
    ) : TestQuestions {
        override fun enterQuestion(
            questionInputUi: TextInputUi,
            answer1Ui: InputAndRadioUi,
            answer2Ui: InputAndRadioUi,
            answer3Ui: InputAndRadioUi,
            answer4Ui: InputAndRadioUi
        ) {
            questionInputUi.replaceInputText(questionText)
            answer1Ui.enterTextAndChecked(answer1, correctAnswer == 0)
            answer2Ui.enterTextAndChecked(answer2, correctAnswer == 1)
            answer3Ui.enterTextAndChecked(answer3, correctAnswer == 2)
            answer4Ui.enterTextAndChecked(answer4, correctAnswer == 3)
        }

        override fun assertQuestion(
            questionInputUi: TextInputUi,
            answer1Ui: InputAndRadioUi,
            answer2Ui: InputAndRadioUi,
            answer3Ui: InputAndRadioUi,
            answer4Ui: InputAndRadioUi
        ) {
            questionInputUi.replaceInputText(questionText)
            answer1Ui.assertTextAndChecked(answer1, correctAnswer == 0)
            answer2Ui.assertTextAndChecked(answer2, correctAnswer == 1)
            answer3Ui.assertTextAndChecked(answer3, correctAnswer == 2)
            answer4Ui.assertTextAndChecked(answer4, correctAnswer == 3)
        }
    }

    data object Idle : Abstract(
        "",
        "",
        "",
        "",
        "",
        0
    )

    data object Number1 : Abstract(
        "Question number 1",
        "1",
        "2",
        "3",
        "4",
        1
    )

    data object Number2 : Abstract(
        "Question number 2",
        "1",
        "2",
        "3",
        "4",
        2
    )
}
