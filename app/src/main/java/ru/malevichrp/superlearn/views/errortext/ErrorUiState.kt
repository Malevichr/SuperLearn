package ru.malevichrp.superlearn.views.errortext

import android.view.View
import java.io.Serializable

interface ErrorUiState : Serializable {
    fun update(updateError: UpdateError)

    object Hide : ErrorUiState {
        override fun update(updateError: UpdateError) {
            updateError.updateVisibility(View.GONE)
        }
    }

    class ShowRes(private val textResId: Int) : ErrorUiState {
        override fun update(updateError: UpdateError) {
            updateError.updateVisibility(View.VISIBLE)
            updateError.updateText(textResId)
        }
    }

    class ShowMessage(private val text: String) : ErrorUiState {
        override fun update(updateError: UpdateError) {
            updateError.updateVisibility(View.VISIBLE)
            updateError.updateText(text)
        }
    }
}
