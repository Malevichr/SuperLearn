package ru.malevichrp.superlearn.views.visibilitybutton

import android.view.View
import java.io.Serializable

interface VisibilityUiState : Serializable {
    fun update(visibilityButton: UpdateVisibility)

    abstract class Abstract(private val visibility: Int) : VisibilityUiState {
        override fun update(visibilityButton: UpdateVisibility) =
            visibilityButton.update(visibility)
    }

    object Visible : Abstract(View.VISIBLE)
    object Invisible : Abstract(View.INVISIBLE)
    object Gone : Abstract(View.GONE)
}