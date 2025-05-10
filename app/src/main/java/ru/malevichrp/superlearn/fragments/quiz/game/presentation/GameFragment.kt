package ru.malevichrp.superlearn.fragments.quiz.game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentGameBinding
import ru.malevichrp.superlearn.fragments.quiz.gameover.presentation.NavigateToGameOver
import ru.malevichrp.superlearn.fragments.quiz.views.visibilitybutton.UpdateVisibility

class GameFragment : AbstractFragment.Async<GameUiState, GameViewModel, FragmentGameBinding>() {

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameBinding =
        FragmentGameBinding.inflate(inflater, container, false)

    override val update = { uiState: GameUiState ->
        requireActivity().runOnUiThread {
            uiState.update(
                binding.questionTextView,
                binding.firstChoiceButton,
                binding.secondChoiceButton,
                binding.thirdChoiceButton,
                binding.forthChoiceButton,
                binding.nextButton ,
                binding.checkButton
            )
            uiState.navigate(requireActivity() as NavigateToGameOver)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel)
            .provideViewModel(GameViewModel::class.java)

        binding.firstChoiceButton.setOnClickListener {
            val uiState = viewModel.chooseFirst()
            update(uiState)
        }
        binding.secondChoiceButton.setOnClickListener {
            val uiState = viewModel.chooseSecond()
            update(uiState)
        }
        binding.thirdChoiceButton.setOnClickListener {
            val uiState = viewModel.chooseThird()
            update(uiState)
        }
        binding.forthChoiceButton.setOnClickListener {
            val uiState = viewModel.chooseForth()
            update(uiState)
        }
        binding.checkButton.setOnClickListener {
            viewModel.check()
        }
        binding.nextButton.setOnClickListener {
            viewModel.next()
        }

        viewModel.init(savedInstanceState == null)
    }
}