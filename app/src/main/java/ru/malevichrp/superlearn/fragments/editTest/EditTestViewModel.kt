package ru.malevichrp.superlearn.fragments.editTest

import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync
import ru.malevichrp.superlearn.views.recycler.TextItems

class EditTestViewModel(
    private val repository: EditTestRepository,
    runAsync: RunAsync,
    private val editTestUiObservable: EditTestUiObservable
) : MyViewModel.Async.Abstract<EditTestUiState>(
    runAsync,
    editTestUiObservable
) {
    fun addQuestion() {
        repository.changeTargetEdiQuestion(-1, isNew = true)
        editTestUiObservable.postUiState(EditTestUiState.NavigateToEditQuestionState())
    }

    fun loadThemes() {
        handleAsync {
            val themes = try {
                repository.loadQuestions()
            } catch (_: Exception) {
                TextItems()
            }
            EditTestUiState.ShowQuestions(themes)
        }
    }

    fun navigateToTargetQuestion(questionId: Int) {
        repository.changeTargetEdiQuestion(questionId, isNew = false)
        editTestUiObservable.postUiState(EditTestUiState.NavigateToEditQuestionState())
    }
}
