package ru.malevichrp.superlearn.fragments.quiz.views.progress

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.ProgressBar
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.UpdateVisibility
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.VisibilitySavedState
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.VisibilityUiState

class ProgressView : ProgressBar, UpdateVisibility {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var state: VisibilityUiState
    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as VisibilitySavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

    override fun update(visibilityUiState: VisibilityUiState) {
        state = visibilityUiState
        state.update(this)
    }
}
