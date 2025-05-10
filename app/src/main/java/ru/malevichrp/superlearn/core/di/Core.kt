package ru.malevichrp.superlearn.core.di

import android.content.Context
import android.content.SharedPreferences
import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.data.learn.LearnCacheModule
import ru.malevichrp.superlearn.core.presentation.RunAsync

class Core(
    context: Context,
    val clearViewModel: ClearViewModel
) {
    val runAsync: RunAsync = RunAsync.Base()
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("slAppData", Context.MODE_PRIVATE)
    val quizCacheModule: LearnCacheModule = LearnCacheModule.Base(context)

    val runUiTests = false
    val size = 1000
    val cacheModule = LearnCacheModule.Base(context)
    val sharedCollection = SharedCollection(sharedPreferences)
}

class SharedCollection(sharedPreferences: SharedPreferences) {
    val targetIsNew = BooleanCache.Base(sharedPreferences, "targetIsNew", false)
    val targetThemeId = IntCache.Base(sharedPreferences, "targetThemeId", -1)
    val isEditUser = BooleanCache.Base(sharedPreferences, "isEditUser", true)
    val correctsInQuiz = IntCache.Base(sharedPreferences, "correctsInQuiz", 0)
    val incorrectsInQuiz = IntCache.Base(sharedPreferences, "incorrectsInQuiz", 0)
    val quizIndex = IntCache.Base(sharedPreferences, "quizIndexKey", 0)
    val userChoiceIndex = IntCache.Base(sharedPreferences, "quizUserChoiceIndex", -1)
    val targetEditQuestionId = IntCache.Base(sharedPreferences, "targetEditQuestionId", -1)
    val targetEditQuestionIsNew =
        BooleanCache.Base(sharedPreferences, "targetEditQuestionIsNew", true)
}