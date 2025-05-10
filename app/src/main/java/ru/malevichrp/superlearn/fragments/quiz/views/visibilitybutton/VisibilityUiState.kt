package ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton

import android.view.View
import java.io.Serializable

interface VisibilityUiState : Serializable {
    fun update(visibilityView: UpdateVisibility)

    abstract class Abstract(private val visibility: Int) : VisibilityUiState {
        override fun update(visibilityView: UpdateVisibility) =
            visibilityView.update(visibility)
    }

    object Visible : Abstract(View.VISIBLE)
    object Invisible : Abstract(View.INVISIBLE)
    object Gone : Abstract(View.GONE)


}