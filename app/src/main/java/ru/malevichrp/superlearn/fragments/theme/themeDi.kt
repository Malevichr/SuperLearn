package ru.malevichrp.superlearn.fragments.theme

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.IntCache
import ru.malevichrp.superlearn.core.data.StringCache
import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel

class ProvideThemeViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    ThemeViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = ThemeModule(core)
}

class ThemeModule(private val core: Core) : Module<ThemeViewModel> {
    override fun viewModel(): ThemeViewModel {
        val repository = ThemeRepository.Base(
            targetIsNew = core.sharedCollection.targetIsNew,
            targetThemeId = core.sharedCollection.targetThemeId,
            isEdit = core.sharedCollection.isEditUser,
            learnDao = core.cacheModule.learnDao()
        )
        return ThemeViewModel(
            repository
        )
    }
}