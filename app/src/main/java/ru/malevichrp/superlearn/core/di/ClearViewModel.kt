package ru.malevichrp.superlearn.core.di

import ru.malevichrp.superlearn.core.presentation.MyViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}