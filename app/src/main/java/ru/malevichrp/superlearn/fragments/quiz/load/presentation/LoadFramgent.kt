package ru.malevichrp.superlearn.fragments.quiz.load.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.fragments.quiz.game.presentation.NavigateToGame
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentLoadBinding
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.UpdateVisibility

class LoadFragment : AbstractFragment.Async<LoadUiState, LoadViewModel, FragmentLoadBinding>() {


    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentLoadBinding =
        FragmentLoadBinding.inflate(inflater, container, false)

    override val update: (LoadUiState) -> Unit = { uiState: LoadUiState ->
        requireActivity().runOnUiThread {
            uiState.show(
                binding.errorTextView,
                binding.retryButton,
                binding.progressBar
            )
            uiState.navigate((requireActivity() as NavigateToGame))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(LoadViewModel::class.java)

        binding.retryButton.setOnClickListener {
            viewModel.load()
        }


        viewModel.load(isFirstRun = savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUpdates(observer = update)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdates()
    }


}