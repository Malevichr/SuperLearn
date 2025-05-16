package ru.malevichrp.superlearn.fragments.quiz.gameover.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentGameOverBinding

class GameOverFragment : AbstractFragment.BindingUi<FragmentGameOverBinding>() {
    private lateinit var viewModel: GameOverViewModel

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameOverBinding =
        FragmentGameOverBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(GameOverViewModel::class.java)

        binding.newGameButton.setOnClickListener {
            viewModel.clear()
            parentFragmentManager.popBackStack(
                "GameScreen",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
        val uiState = viewModel.init(savedInstanceState == null)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backCallback)
        binding.statsTextView.update(uiState)
    }

    private val backCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            parentFragmentManager.popBackStack(
                "GameScreen",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    override fun onResume() {
        super.onResume()
        backCallback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        backCallback.isEnabled = false
    }
}
