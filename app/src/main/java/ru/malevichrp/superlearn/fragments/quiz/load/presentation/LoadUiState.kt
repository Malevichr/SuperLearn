package ru.malevichrp.superlearn.fragments.quiz.load.presentation

import ru.malevichrp.superlearn.fragments.quiz.game.presentation.NavigateToGame
import ru.malevichrp.superlearn.R
import ru.malevichrp.superlearn.core.presentation.UiState
import ru.malevichrp.superlearn.fragments.quiz.views.error.ErrorUiState
import ru.malevichrp.superlearn.fragments.quiz.views.error.UpdateError
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.UpdateVisibility
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.VisibilityUiState

interface LoadUiState : UiState {
    fun show(
        errorTextView: UpdateError,
        retryButton: UpdateVisibility,
        progressBar: UpdateVisibility
    )

    fun navigate(navigateToGame: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorUiState: ErrorUiState,
        private val retryVisibility: VisibilityUiState,
        private val progressVisibility: VisibilityUiState
    ) : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            errorTextView.update(errorUiState)
            retryButton.update(retryVisibility)
            progressBar.update(progressVisibility)
        }
    }

    data class ErrorRes(private val messageId: Int = R.string.no_internet_connection) : Abstract(
        errorUiState = ErrorUiState.ShowRes(messageId),
        retryVisibility = VisibilityUiState.Visible,
        progressVisibility = VisibilityUiState.Gone
    )

    data class Error(private val message: String) : Abstract(
        errorUiState = ErrorUiState.ShowMessage(message),
        retryVisibility = VisibilityUiState.Visible,
        progressVisibility = VisibilityUiState.Gone
    )


    object Progress : Abstract(
        errorUiState = ErrorUiState.Hide,
        retryVisibility = VisibilityUiState.Gone,
        progressVisibility = VisibilityUiState.Visible
    )

    object Success : Abstract(
        errorUiState = ErrorUiState.Hide,
        retryVisibility = VisibilityUiState.Gone,
        progressVisibility = VisibilityUiState.Gone
    ) {
        override fun navigate(navigateToGame: NavigateToGame) {
            navigateToGame.navigateToGame()
        }
    }

}
