package ru.malevichrp.superlearn.fragments.quiz.views.questiontextview

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes

class QuestionTextView : androidx.appcompat.widget.AppCompatTextView, UpdateText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(text: String) {
        this.text = text
    }

    override fun update(textResId: Int) {
        setText(textResId)
    }

    override fun getFreezesText(): Boolean = true

}

interface UpdateText {
    fun update(text: String)
    fun update(@StringRes textResId: Int)
}