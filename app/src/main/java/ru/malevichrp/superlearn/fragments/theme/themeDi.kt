package ru.malevichrp.superlearn.fragments.theme

import ru.malevichrp.superlearn.core.data.BooleanCache
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
        val repository = ThemeRepository.Fake(
            targetIsNew = BooleanCache.Base(core.sharedPreferences, "targetIsNew", false),
            targetThemeText = StringCache.Base(core.sharedPreferences, "targetThemeText", "New Theme"),
            isEdit = BooleanCache.Base(core.sharedPreferences, "isEdit", true)
        )
        return ThemeViewModel(
            repository
        )
    }
}