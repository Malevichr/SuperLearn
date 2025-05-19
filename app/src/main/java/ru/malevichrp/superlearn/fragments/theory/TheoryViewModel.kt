package ru.malevichrp.superlearn.fragments.theory

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.core.presentation.RunAsync
import ru.malevichrp.superlearn.fragments.theory.theoryRecycler.TheoryRecyclerItem

class TheoryViewModel(
    private val repository: TheoryRepository,
    observable: TheoryUiObservable,
    runAsync: RunAsync
) : MyViewModel.Async.Abstract<TheoryUiState>(
    runAsync,
    observable
) {
    fun addImage(uri: Uri) {
        handleAsync {
            val items: List<TheoryItem> = repository.addImageAndText(uri.toString())
            val transformedItems = transformItems(items)
            TheoryUiState.Redactor(transformedItems)
        }
    }

    fun deleteImage(id: Int) {
        handleAsync {
            val items: List<TheoryItem> = repository.deleteImage(imageId = id)
            val transformedItems = transformItems(items)
            TheoryUiState.Redactor(transformedItems)
        }
    }

    fun init(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            handleAsync {
                val items: List<TheoryItem> = repository.items()
                val transformedItems = transformItems(items)
                TheoryUiState.Redactor(transformedItems)
            }
        }
    }

    private fun transformItems(items: List<TheoryItem>) =
        items.map {
            if (it.isImage)
                TheoryRecyclerItem.Image(Uri.parse(it.uri), it.id)
            else
                TheoryRecyclerItem.Text(it.text, it.id)
        }

    fun inputText(id: Int, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeInputText(id = id, text = text)
        }
    }
}
