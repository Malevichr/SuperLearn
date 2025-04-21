package ru.malevichrp.superlearn.fragments.quiz.views.statstextview

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class StatsSavedState : View.BaseSavedState {

    private lateinit var state: StatsUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                StatsUiState::class.java.classLoader,
                StatsUiState::class.java
            ) as StatsUiState
        } else {
            parcelIn.readSerializable() as StatsUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): StatsUiState = state

    fun save(uiState: StatsUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<StatsSavedState> {
        override fun createFromParcel(parcel: Parcel): StatsSavedState =
            StatsSavedState(parcel)

        override fun newArray(size: Int): Array<StatsSavedState?> =
            arrayOfNulls(size)
    }
}