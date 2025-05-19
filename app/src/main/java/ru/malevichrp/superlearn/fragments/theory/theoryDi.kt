package ru.malevichrp.superlearn.fragments.theory

import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel

class ProvideTheoryViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    TheoryViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = TheoryModule(core)
}

class TheoryModule(private val core: Core) : Module<TheoryViewModel> {
    override fun viewModel(): TheoryViewModel {
        return TheoryViewModel(
            TheoryRepository.Base(
                core.sharedCollection.targetThemeId,
                core.cacheModule.theoryDao()
            ),
            TheoryUiObservable.Base(),
            core.runAsync
        )
    }
}