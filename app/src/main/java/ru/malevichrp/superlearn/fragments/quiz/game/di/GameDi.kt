package ru.malevichrp.superlearn.fragments.quiz.game.di

import ru.malevichrp.superlearn.fragments.quiz.game.presentation.GameUiObservable
import ru.malevichrp.superlearn.fragments.quiz.game.presentation.GameViewModel
import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.fragments.quiz.game.data.GameRepository

class GameModule(
    private val core: Core
) : Module<GameViewModel> {
    override fun viewModel(): GameViewModel {
        return if (core.runUiTests)
            GameViewModel(
                repository = GameRepository.Fake(
                    index = IntCache.Base(core.sharedPreferences, "indexKey", core.size),
                    userChoiceIndex = IntCache.Base(core.sharedPreferences, "userChoiceIndex", -1),
                    corrects = IntCache.Base(core.sharedPreferences, "corrects", 0),
                    incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0),
                    ),
                clearViewModel = core.clearViewModel,
                observable = GameUiObservable.Base(),
                runAsync = core.runAsync
            )
        else
            GameViewModel(
                repository = GameRepository.Base(
                    index = IntCache.Base(core.sharedPreferences, "indexKey", core.size),
                    userChoiceIndex = IntCache.Base(core.sharedPreferences, "userChoiceIndex", -1),
                    corrects = IntCache.Base(core.sharedPreferences, "corrects", 0),
                    incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0),
                    dao = core.cacheModule.dao(),
                    clearDatabase = core.cacheModule.clearDatabase(),
                    size = core.size
                ),
                clearViewModel = core.clearViewModel,
                observable = GameUiObservable.Base(),
                runAsync = core.runAsync
            )
    }
}

class ProvideGameViewModel(core: Core, nextLink: ProvideViewModel) :
    ProvideViewModel.AbstractChainLink(
        core,
        nextLink,
        GameViewModel::class.java
    ) {
    override fun module(): Module<out MyViewModel> =
        GameModule(core)
}