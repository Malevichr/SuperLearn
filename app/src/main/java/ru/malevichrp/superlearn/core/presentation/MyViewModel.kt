package ru.malevichrp.superlearn.core.presentation

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


interface MyViewModel {
    interface Async<T : UiState> : MyViewModel {
        fun startUpdates(observer: (T) -> Unit)
        fun stopUpdates()
        fun handleAsync(heavyOperation: suspend () -> T)

        abstract class Abstract<T : UiState>(
            private val runAsync: RunAsync,
            protected val observable: UiObservable<T>
        ) : Async<T> {
            private val viewModelScope =
                CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

            private val updateUi = { uiState: T ->
                observable.postUiState(uiState)
            }

            override fun handleAsync(heavyOperation: suspend () -> T) {
                runAsync.runAsync(
                    coroutineScope = viewModelScope,
                    heavyOperation = heavyOperation,
                    uiUpdate = updateUi
                )
            }

            override fun startUpdates(observer: (T) -> Unit) {
                observable.register(observer)
            }

            override fun stopUpdates() {
                observable.unregister()
            }
        }
    }
}