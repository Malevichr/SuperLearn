package ru.malevichrp.superlearn.views.errortext


import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View


class ErrorSavedState : View.BaseSavedState {

    private lateinit var state: ErrorUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                ErrorUiState::class.java.classLoader,
                ErrorUiState::class.java
            ) as ErrorUiState
        } else {
            parcelIn.readSerializable() as ErrorUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ErrorUiState = state

    fun save(uiState: ErrorUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ErrorSavedState> {
        override fun createFromParcel(parcel: Parcel): ErrorSavedState =
            ErrorSavedState(parcel)

        override fun newArray(size: Int): Array<ErrorSavedState?> =
            arrayOfNulls(size)
    }
}