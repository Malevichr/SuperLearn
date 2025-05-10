package ru.malevichrp.superlearn.fragments.editTest

import ru.malevichrp.superlearn.core.presentation.UiState
import ru.malevichrp.superlearn.fragments.editQuestion.NavigateToEditQuestion
import ru.malevichrp.superlearn.views.recycler.TextAdapter
import ru.malevichrp.superlearn.views.recycler.TextItems

interface EditTestUiState : UiState {
    fun update(adapter: TextAdapter) = Unit
    fun navigate(navigate: NavigateToEditQuestion) = Unit
    class ShowQuestions(private val items: TextItems) : EditTestUiState{
        override fun update(adapter: TextAdapter) {
            adapter.addAll(items)
        }
    }
    class NavigateToEditQuestionState : EditTestUiState{
        override fun navigate(navigate: NavigateToEditQuestion) {
            navigate.navigateToEditQuestion()
        }
    }
}
