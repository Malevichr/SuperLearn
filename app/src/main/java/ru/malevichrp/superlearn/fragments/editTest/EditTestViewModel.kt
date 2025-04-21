package ru.malevichrp.superlearn.fragments.editTest

import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync

class EditTestViewModel(
    private val repository: EditTestRepository,
    runAsync: RunAsync,
    private val editTestUiObservable: EditTestUiObservable
) : MyViewModel.Async.Abstract<EditTestUiState>(
    runAsync,
    editTestUiObservable
) {
    fun addQuestion() {
        repository.changeTargetEdiQuestion("New question", isNew = true)
        editTestUiObservable.postUiState(EditTestUiState.NavigateToEditQuestionState())
    }

    fun loadThemes() {
        handleAsync {
            val themes = try {
                repository.loadQuestions()
            } catch (_: Exception) {
                arrayListOf()
            }
            EditTestUiState.ShowQuestions(themes)
        }
    }

    fun navigateToTargetQuestion(questionText: String) {
        repository.changeTargetEdiQuestion(questionText, isNew = false)
        editTestUiObservable.postUiState(EditTestUiState.NavigateToEditQuestionState())
    }
}
