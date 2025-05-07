package ru.malevichrp.superlearn.fragments.theme

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.malevichrp.superlearn.core.presentation.MyViewModel

class ThemeViewModel(
    private val repository: ThemeRepository
) : MyViewModel {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun changeThemeName(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeThemeName(text)
        }
    }

    fun deleteCurrentTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTargetTheme()
        }
    }


    fun isEdit(): Boolean =
        repository.isEdit()

    fun initTheme(updateText: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val title: String = repository.initTheme()
            withContext(Dispatchers.Main){
                updateText.invoke(title)
            }
        }

    }
}
