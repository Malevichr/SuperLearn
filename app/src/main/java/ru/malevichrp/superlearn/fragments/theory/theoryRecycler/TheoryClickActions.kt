package ru.malevichrp.superlearn.fragments.theory.theoryRecycler

interface TheoryClickActions {
    interface Mutable : Hold, InputText
    interface Hold : TheoryClickActions {
        fun hold(id: Int)
    }

    interface InputText : TheoryClickActions {
        fun inputText(id: Int, text: String)
    }
}