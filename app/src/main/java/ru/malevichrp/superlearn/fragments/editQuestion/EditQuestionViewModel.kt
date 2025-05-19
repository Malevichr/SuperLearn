package ru.malevichrp.superlearn.fragments.editQuestion

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync

class EditQuestionViewModel(
    private val repository: EditQuestionRepository,
    private val runAsync: RunAsync
) : MyViewModel {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun deleteQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTargetQuestion()
        }
    }

    fun updateQuestion(question: Question) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuestion(question)
        }
    }

    fun initQuestion(callback: (Question) -> Unit, isFirstRun: Boolean = true) {
        if (isFirstRun) {
            runAsync.runAsync(
                viewModelScope,
                heavyOperation = {
                    repository.targetQuestion()
                },
                uiUpdate = {
                    callback.invoke(it)
                }
            )
        }
    }

    fun addNewQuestion(oldQuestion: Question, callback: (Question) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuestion(oldQuestion)
            repository.createNew()
            initQuestion(callback)
        }
    }
}
