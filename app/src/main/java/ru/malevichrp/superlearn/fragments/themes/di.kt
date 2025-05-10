package ru.malevichrp.superlearn.fragments.themes

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel

class ProvideThemesViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    ThemesViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = ThemesModule(core)
}

class ThemesModule(private val core: Core) : Module<ThemesViewModel>{
    override fun viewModel(): ThemesViewModel {
        val repository = ThemesRepository.Base(
            targetIsNew = core.sharedCollection.targetIsNew,
            targetThemeId = core.sharedCollection.targetThemeId,
            learnDao = core.cacheModule.learnDao()
        )
        return ThemesViewModel(
            repository,
            core.runAsync,
            ThemesUiObservable.Base()
        )
    }
}