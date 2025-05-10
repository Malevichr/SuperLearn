package ru.malevichrp.superlearn.core.presentation

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface RunAsync {
    fun <T : Any> runAsync(
        coroutineScope: CoroutineScope,
        heavyOperation: suspend () -> T,
        uiUpdate: (T) -> Unit
    )

    class Base : RunAsync {
        override fun <T : Any> runAsync(
            coroutineScope: CoroutineScope,
            heavyOperation: suspend () -> T,
            uiUpdate: (T) -> Unit
        ) {
            coroutineScope.launch(Dispatchers.IO) {
                val result = heavyOperation.invoke()
                withContext(Dispatchers.Main) {
                    Log.d("mlvc", "trying to show")
                    uiUpdate.invoke(result)
                }
            }
        }
    }
}
