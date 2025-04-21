package ru.malevichrp.superlearn.fragments.quiz.views.choicebutton


import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View


class ChoiceButtonSavedState : View.BaseSavedState {

    private lateinit var state: ChoiceUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                ChoiceUiState::class.java.classLoader,
                ChoiceUiState::class.java
            ) as ChoiceUiState
        } else {
            parcelIn.readSerializable() as ChoiceUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ChoiceUiState = state

    fun save(uiState: ChoiceUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ChoiceButtonSavedState> {
        override fun createFromParcel(parcel: Parcel): ChoiceButtonSavedState =
            ChoiceButtonSavedState(parcel)

        override fun newArray(size: Int): Array<ChoiceButtonSavedState?> =
            arrayOfNulls(size)
    }
}