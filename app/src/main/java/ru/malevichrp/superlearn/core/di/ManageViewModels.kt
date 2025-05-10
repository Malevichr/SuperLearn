package ru.malevichrp.superlearn.core.di

import ru.malevichrp.superlearn.core.presentation.MyViewModel

interface ManageViewModels : ProvideViewModel, ClearViewModel {
    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {
        private val viewModelsMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T {
            return if (viewModelsMap[clazz] == null) {
                val viewModel = make.provideViewModel(clazz)
                viewModelsMap[clazz] = viewModel
                viewModel
            } else
                viewModelsMap[clazz] as T
        }

        override fun clear(viewModelClass: Class<out MyViewModel>) {
            viewModelsMap[viewModelClass] = null
        }
    }
}