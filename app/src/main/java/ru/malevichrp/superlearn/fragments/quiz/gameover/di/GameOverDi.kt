package ru.malevichrp.superlearn.fragments.quiz.gameover.di

import ru.malevichrp.superlearn.fragments.quiz.gameover.data.GameOverRepository
import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.fragments.quiz.gameover.presentation.GameOverViewModel

class ProvideGameOverViewModel(
    core: Core,
    nextLink: ProvideViewModel,
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    GameOverViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> =
        GameOverModule(core)
}

class GameOverModule(
    private val core: Core
) : Module<GameOverViewModel> {
    override fun viewModel(): GameOverViewModel =
        GameOverViewModel(
            GameOverRepository.Base(
                IntCache.Base(core.sharedPreferences, "corrects", 0),
                IntCache.Base(core.sharedPreferences, "incorrects", 0)
            ),
            core.clearViewModel
        )

}