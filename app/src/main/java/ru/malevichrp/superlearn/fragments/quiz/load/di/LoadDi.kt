package ru.malevichrp.superlearn.fragments.quiz.load.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.fragments.quiz.load.data.LoadRepository
import ru.malevichrp.superlearn.fragments.quiz.load.data.cache.CloudDataSource
import ru.malevichrp.superlearn.fragments.quiz.load.data.cloud.QuizService
import ru.malevichrp.superlearn.fragments.quiz.load.presentation.LoadUiObservable
import ru.malevichrp.superlearn.fragments.quiz.load.presentation.LoadViewModel
import java.util.concurrent.TimeUnit

class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    ProvideViewModel.AbstractChainLink(core, next, LoadViewModel::class.java) {

    override fun module(): Module<out MyViewModel> = LoadModule(core)
}

class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return LoadViewModel(
            if (core.runUiTests)
                LoadRepository.Fake()
            else
                LoadRepository.Base(
                    core.quizCacheModule.questionAndChoicesDao(),
                    CloudDataSource.Base(
                        retrofit.create(QuizService::class.java),
                        core.size,
                    ),
                ),
            LoadUiObservable.Base(),
            core.runAsync,
            core.clearViewModel
        )
    }
}