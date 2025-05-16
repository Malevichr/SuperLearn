package ru.malevichrp.superlearn.fragments.quiz.game.presentation

import ru.malevichrp.superlearn.core.presentation.UiState
import ru.malevichrp.superlearn.fragments.quiz.gameover.presentation.NavigateToGameOver
import ru.malevichrp.superlearn.fragments.quiz.views.choicebutton.ChoiceUiState
import ru.malevichrp.superlearn.fragments.quiz.views.choicebutton.UpdateChoiceButton
import ru.malevichrp.superlearn.fragments.quiz.views.questiontextview.UpdateText
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.UpdateVisibility
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.VisibilityUiState
import java.io.Serializable

interface GameUiState : Serializable, UiState {

    fun update(
        questionTextView: UpdateText,
        firstChoiceButton: UpdateChoiceButton,
        secondChoiceButton: UpdateChoiceButton,
        thirdChoiceButton: UpdateChoiceButton,
        forthChoiceButton: UpdateChoiceButton,
        nextButton: UpdateVisibility,
        checkButton: UpdateVisibility
    ) = Unit

    fun navigate(navigate: NavigateToGameOver) = Unit

    object Empty : GameUiState {
        private fun readResolve(): Any = Empty
    }

    object Finish : GameUiState {
        private fun readResolve(): Any = Finish
        override fun navigate(navigate: NavigateToGameOver) {
            return navigate.navigateToGameOver()
        }
    }

    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            questionTextView.update(question)
            firstChoiceButton.update(ChoiceUiState.Initial(choices[0]))
            secondChoiceButton.update(ChoiceUiState.Initial(choices[1]))
            thirdChoiceButton.update(ChoiceUiState.Initial(choices[2]))
            forthChoiceButton.update(ChoiceUiState.Initial(choices[3]))
            nextButton.update(VisibilityUiState.Gone)
            checkButton.update(VisibilityUiState.Invisible)
        }
    }

    data class ChoiceMade(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            forthChoiceButton.update(choices[3])
            checkButton.update(VisibilityUiState.Visible)
        }
    }

    data class AnswerCheckedState(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            forthChoiceButton.update(choices[3])
            nextButton.update(VisibilityUiState.Visible)
            checkButton.update(VisibilityUiState.Gone)
        }
    }
}

