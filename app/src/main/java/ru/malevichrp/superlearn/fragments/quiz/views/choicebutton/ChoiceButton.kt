package ru.malevichrp.superlearn.fragments.quiz.views.choicebutton

import android.content.Context
import android.graphics.Color
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import ru.malevichrp.superlearn.fragments.quiz.views.questiontextview.UpdateText


class ChoiceButton : AppCompatButton, UpdateChoiceButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = ChoiceButtonSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ChoiceButtonSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    private lateinit var state: ChoiceUiState

    override fun getFreezesText(): Boolean = true

    override fun update(choiceUiState: ChoiceUiState) {
        this.state = choiceUiState
        state.update(this)
    }

    override fun update(color: String, isClickable: Boolean, isEnabled: Boolean) {
        this.isEnabled = isEnabled
        this.isClickable = isClickable
        setBackgroundColor(Color.parseColor(color))
    }

    override fun update(text: String) {
        this.text = text
    }

    override fun update(textResId: Int) {
        setText(textResId)
    }


}

interface UpdateChoiceButton : UpdateText {
    fun update(choiceUiState: ChoiceUiState)
    fun update(
        color: String,
        isClickable: Boolean,
        isEnabled: Boolean
    )
}