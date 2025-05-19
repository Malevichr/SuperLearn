package ru.malevichrp.superlearn.fragments.theory

import ru.malevichrp.superlearn.core.presentation.UiState
import ru.malevichrp.superlearn.fragments.theory.theoryRecycler.TheoryRecyclerAdapter
import ru.malevichrp.superlearn.fragments.theory.theoryRecycler.TheoryRecyclerItem
import ru.malevichrp.superlearn.views.visibilitybutton.VisibilityButton
import ru.malevichrp.superlearn.views.visibilitybutton.VisibilityUiState

interface TheoryUiState : UiState {
    fun show(addMediaButton: VisibilityButton, adapter: TheoryRecyclerAdapter)
    class Redactor(private val items: List<TheoryRecyclerItem>) : TheoryUiState {
        override fun show(addMediaButton: VisibilityButton, adapter: TheoryRecyclerAdapter) {
            addMediaButton.update(VisibilityUiState.Visible)
            adapter.update(items)
        }
    }

    class Viewer(private val items: List<TheoryRecyclerItem>) : TheoryUiState {
        override fun show(addMediaButton: VisibilityButton, adapter: TheoryRecyclerAdapter) {
            addMediaButton.update(VisibilityUiState.Gone)
            adapter.update(items)
        }
    }
}