package ru.malevichrp.superlearn.core.presentation

interface UiObservable<T : Any> {
    fun postUiState(uiState: T)

    fun register(observer: (T) -> Unit)

    fun unregister()

    abstract class Abstract<T : Any> : UiObservable<T> {
        private var uiStateCached: T? = null
        private var observerCached: ((T) -> Unit)? = null

        override fun postUiState(uiState: T) {
            if (observerCached != null) {
                observerCached?.invoke(uiState)
                uiStateCached = null
            } else
                uiStateCached = uiState
        }

        override fun register(observer: (T) -> Unit) {
            observerCached = observer
            if (uiStateCached != null) {
                observerCached?.invoke(uiStateCached!!)
                uiStateCached = null
            }
        }

        override fun unregister() {
            this.observerCached = null
        }
    }
}