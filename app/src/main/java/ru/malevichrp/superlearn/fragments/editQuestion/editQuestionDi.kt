package ru.malevichrp.superlearn.fragments.editQuestion

import ru.malevichrp.superlearn.core.data.StringCache
import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel

class ProvideEditQuestionViewModel(
    core: Core, nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core, nextLink, EditQuestionViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> {
        return EditQuestionModule(core)
    }
}

class EditQuestionModule(
    private val core: Core
) : Module<EditQuestionViewModel> {
    override fun viewModel(): EditQuestionViewModel {
        val repository = EditQuestionRepository.Fake(
            targetQuestionText = StringCache.Base(
                sharedPreferences = core.sharedPreferences,
                key = "targetQuestionText",
                defaultValue = "None"
            )
        )
        return EditQuestionViewModel(repository, core.runAsync)
    }
}