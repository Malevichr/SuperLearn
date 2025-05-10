package ru.malevichrp.superlearn.fragments.quiz.load.presentation

import ru.malevichrp.superlearn.fragments.quiz.load.data.BackendError
import ru.malevichrp.superlearn.fragments.quiz.load.data.LoadRepository
import ru.malevichrp.superlearn.fragments.quiz.load.data.NoInternetConnectionException
import ru.malevichrp.superlearn.R
import ru.malevichrp.superlearn.core.di.ClearViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync

class LoadViewModel(
    private val repository: LoadRepository,
    observable: LoadUiObservable,
    private val runAsync: RunAsync,
    private val clearViewModel: ClearViewModel
) : MyViewModel.Async.Abstract<LoadUiState>(runAsync, observable), MyViewModel {
    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            handleAsync {
                try {
                    val loadResult = repository.load()
                    clearViewModel.clear(this.javaClass)
                    LoadUiState.Success
                } catch (e: Exception) {
                    when (e) {
                        is NoInternetConnectionException -> LoadUiState.ErrorRes()
                        is BackendError -> LoadUiState.Error(e.message)
                        else -> LoadUiState.ErrorRes(R.string.service_unavailable)
                    }
                }
            }
        }
    }
}
