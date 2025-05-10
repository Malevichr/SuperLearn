package ru.malevichrp.superlearn.core.di

interface Module<T> {
    fun viewModel(): T
}