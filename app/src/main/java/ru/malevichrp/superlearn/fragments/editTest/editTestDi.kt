package ru.malevichrp.superlearn.fragments.editTest

import ru.malevichrp.superlearn.core.data.BooleanCache
import ru.malevichrp.superlearn.core.data.StringCache
import ru.malevichrp.superlearn.core.di.Core
import ru.malevichrp.superlearn.core.di.Module
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.MyViewModel

class ProvideEditTestViewModel(
    core: Core,
    nextLink: ProvideViewModel
    ) : ProvideViewModel.AbstractChainLink(
        core,
        nextLink,
        EditTestViewModel::class.java
    )
{
    override fun module(): Module<out MyViewModel> =
        EditTestModule(core)

}
class EditTestModule(private val core: Core): Module<EditTestViewModel> {
    override fun viewModel(): EditTestViewModel {
        val repository = EditTestRepository.Fake(
            targetEditQuestionText = StringCache.Base(core.sharedPreferences, "targetEditQuestionText", "new question"),
            targetEditQuestionIsNew = BooleanCache.Base(core.sharedPreferences, "targetEditQuestionIsNew", true)
        )
        return EditTestViewModel(
            repository,
            core.runAsync,
            EditTestUiObservable.Base()
        )
    }
}