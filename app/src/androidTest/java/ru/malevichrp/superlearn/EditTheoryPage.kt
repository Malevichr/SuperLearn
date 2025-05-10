package ru.malevichrp.superlearn

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf
import ru.malevichrp.superlearn.recycler.RecyclerUi
import ru.malevichrp.superlearn.recycler.TheoryInputItemUi

class EditTheoryPage {
    private val containerMatcher = withParent(
        allOf(
            withId(R.id.theoryContainer),
            isAssignableFrom(LinearLayout::class.java)
        )
    )

    private val backButton: ButtonUi = ButtonUi.Base(
        id = R.id.backButton,
        text = R.string.back,
        containerMatcher = withParent(containerMatcher)
    )

    private val addMediaButton: ButtonUi = ButtonUi.Base(
        id = R.id.addMediaButton,
        text = R.string.add_photo_video,
        containerMatcher = withParent(containerMatcher)
    )

    private val recyclerUi: RecyclerUi = RecyclerUi.Base(
        id = R.id.themesRecycler,
        containerMatcher = withParent(containerMatcher)
    )
    private val theoryInputItemUi: TheoryInputItemUi = TheoryInputItemUi.Base(
        id = R.id.item_edit_long_text,
        containerMatcher = containerMatcher,
    )
    private val imageItemUi: ImageItemUi = ImageItemUi.Base(
        R.id.imageItem,
        containerMatcher = containerMatcher
    )

    fun assertIdle() {
        recyclerUi.assertCount(1)
        theoryInputItemUi.assertText("")
    }

    fun enterText(text: String) {
        theoryInputItemUi.inputText(text)
    }

    fun assertOnlyText(text: String) {
        theoryInputItemUi.assertText(text)
        recyclerUi.assertCount(1)
    }

    fun clickAddMedia() {
        addMediaButton.click()
    }

    fun assertOneImageAndText(text: String) {
        imageItemUi.assertVisible()
        theoryInputItemUi.assertText(text)
    }

    fun holdImage() {
        imageItemUi.hold()
    }

    fun clickBack() {
        backButton.click()
    }
}
