package ru.malevichrp.superlearn.fragments.quiz.views.error

import android.view.View
import java.io.Serializable

interface ErrorUiState : Serializable {
    fun update(errorText: UpdateError)

    object Hide : ErrorUiState {
        override fun update(errorText: UpdateError) {
            errorText.updateVisibility(View.GONE)
        }
    }

    data class ShowRes(private val textResId: Int) : ErrorUiState {
        override fun update(errorText: UpdateError) {
            errorText.updateTextResId(textResId)

            errorText.updateVisibility(View.VISIBLE)
        }
    }

    data class ShowMessage(private val message: String) : ErrorUiState {
        override fun update(errorText: UpdateError) {
            errorText.updateText(message)

            errorText.updateVisibility(View.VISIBLE)
        }
    }

}