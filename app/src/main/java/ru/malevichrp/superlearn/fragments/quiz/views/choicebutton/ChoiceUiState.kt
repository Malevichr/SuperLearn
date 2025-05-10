package ru.malevichrp.superlearn.fragments.quiz.views.choicebutton

import java.io.Serializable

interface ChoiceUiState : Serializable {
    fun update(updateChoiceButton: UpdateChoiceButton)
    abstract class Abstract(
        private val color: String,
        private val isClickable: Boolean,
        private val isEnabled: Boolean
    ) : ChoiceUiState {
        override fun update(updateChoiceButton: UpdateChoiceButton) {
            updateChoiceButton.update(
                color,
                isClickable,
                isEnabled
            )
        }
    }

    object NotAvailableToChoose : Abstract(
        color = "#5F6E79",
        isClickable = false,
        isEnabled = false
    )

    data class Initial(private val text: String) : Abstract(
        color = "#58B6FF",
        isClickable = true,
        isEnabled = true
    ) {
        override fun update(updateChoiceButton: UpdateChoiceButton) {
            super.update(updateChoiceButton)
            updateChoiceButton.update(text)
        }
    }

    object AvailableToChoose : Abstract(
        color = "#58B6FF",
        isClickable = true,
        isEnabled = true
    )

    object Correct : Abstract(
        color = "#2EE521",
        isClickable = false,
        isEnabled = true
    )

    object NotCorrect : Abstract(
        color = "#EE2929",
        isClickable = false,
        isEnabled = true
    )
}