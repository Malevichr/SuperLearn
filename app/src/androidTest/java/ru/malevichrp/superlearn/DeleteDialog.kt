package ru.malevichrp.superlearn

import android.widget.Button
import androidx.appcompat.widget.DialogTitle
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

class DeleteDialog {
    private val textUI: Visible = object : AbstractUi(
        allOf(
            withText(R.string.do_you_want_to_delete),
            isAssignableFrom(DialogTitle::class.java)
        )
    ){}
    private val buttonYes: ButtonUi = object : AbstractUi(
        allOf(
            withText(R.string.yes),
            isAssignableFrom(Button::class.java)
        )
    ),ButtonUi{}
    private val buttonNo: ButtonUi = object : AbstractUi(
        allOf(
            withText(R.string.no),
            isAssignableFrom(Button::class.java)
        )
    ),ButtonUi{}
    fun assertVisible() {
        textUI.assertVisible()
        buttonYes.assertVisible()
        buttonNo.assertVisible()
    }

    fun clickYes() {
        buttonYes.click()
    }
}
