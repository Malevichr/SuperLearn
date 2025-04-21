package ru.malevichrp.superlearn.core.di

import android.content.Context
import android.content.SharedPreferences
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.CacheModule
import ru.malevichrp.superlearn.core.presentation.RunAsync

class Core(
    context: Context,
    val clearViewModel: ClearViewModel
) {
    val runAsync: RunAsync = RunAsync.Base()
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("slAppData", Context.MODE_PRIVATE)
    val cacheModule: CacheModule = CacheModule.Base(context)

    val runUiTests = false
    val size = 4
}