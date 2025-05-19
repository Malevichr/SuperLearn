package ru.malevichrp.superlearn.core.di

import ru.malevichrp.superlearn.core.presentation.MyViewModel
import ru.malevichrp.superlearn.fragments.editQuestion.ProvideEditQuestionViewModel
import ru.malevichrp.superlearn.fragments.editTest.ProvideEditTestViewModel
import ru.malevichrp.superlearn.fragments.quiz.game.di.ProvideGameViewModel
import ru.malevichrp.superlearn.fragments.quiz.gameover.di.ProvideGameOverViewModel
import ru.malevichrp.superlearn.fragments.quiz.load.di.ProvideLoadViewModel
import ru.malevichrp.superlearn.fragments.theme.ProvideThemeViewModel
import ru.malevichrp.superlearn.fragments.themes.ProvideThemesViewModel
import ru.malevichrp.superlearn.fragments.theory.ProvideTheoryViewModel

interface ProvideViewModel {
    fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T
    class Make(
        core: Core
    ) : ProvideViewModel {
        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideThemesViewModel(core, chain)
            chain = ProvideThemeViewModel(core, chain)
            chain = ProvideEditTestViewModel(core, chain)
            chain = ProvideEditQuestionViewModel(core, chain)
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
            chain = ProvideLoadViewModel(core, chain)
            chain = ProvideTheoryViewModel(core, chain)
        }

        override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T =
            chain.provideViewModel(clazz)

    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T {
            throw IllegalStateException("Unknown class: $clazz")
        }
    }

    abstract class AbstractChainLink(
        protected val core: Core,
        private val nextLink: ProvideViewModel,
        private val viewModelClass: Class<out MyViewModel>
    ) : ProvideViewModel {
        override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T {
            return if (viewModelClass == clazz)
                module().viewModel() as T
            else
                nextLink.provideViewModel(clazz)
        }

        protected abstract fun module(): Module<out MyViewModel>
    }
}