package ru.malevichrp.superlearn.fragments.quiz.game.di

import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.fragments.quiz.game.data.GameRepository
import ru.malevichrp.superlearn.fragments.quiz.game.presentation.GameUiObservable
import ru.malevichrp.superlearn.fragments.quiz.game.presentation.GameViewModel

class GameModule(
    private val core: Core
) : Module<GameViewModel> {
    override fun viewModel(): GameViewModel {
        return if (core.runUiTests)
            GameViewModel(
                repository = GameRepository.Fake(
                    index = core.sharedCollection.quizIndex,
                    userChoiceIndex = core.sharedCollection.userChoiceIndex,
                    corrects = core.sharedCollection.correctsInQuiz,
                    incorrects = core.sharedCollection.incorrectsInQuiz,
                    ),
                clearViewModel = core.clearViewModel,
                observable = GameUiObservable.Base(),
                runAsync = core.runAsync
            )
        else
            GameViewModel(
                repository = GameRepository.Base(
                    index = core.sharedCollection.quizIndex,
                    userChoiceIndex = core.sharedCollection.userChoiceIndex,
                    corrects = core.sharedCollection.correctsInQuiz,
                    incorrects = core.sharedCollection.incorrectsInQuiz,
                    dao = core.quizCacheModule.questionAndChoicesDao(),
                    size = core.size,
                    targetThemeId = core.sharedCollection.targetThemeId,
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