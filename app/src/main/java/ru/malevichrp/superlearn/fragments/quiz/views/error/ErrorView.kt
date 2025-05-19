package ru.malevichrp.superlearn.fragments.quiz.views.error

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView

class ErrorView : AppCompatTextView, UpdateError {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var state: ErrorUiState
    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = ErrorSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ErrorSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun updateTextResId(textResId: Int) {
        setText(textResId)
    }

    override fun updateVisibility(textVisibility: Int) {
        visibility = textVisibility
    }

    override fun updateText(text: String) {
        this.text = text
    }

    override fun update(uiState: ErrorUiState) {
        state = uiState
        state.update(this)
    }

}

interface UpdateError {
    fun updateTextResId(@StringRes textResId: Int)

    fun updateVisibility(textVisibility: Int)

    fun updateText(text: String)

    fun update(uiState: ErrorUiState)
}