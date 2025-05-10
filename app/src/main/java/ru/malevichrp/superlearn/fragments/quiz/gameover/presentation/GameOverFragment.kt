package ru.malevichrp.superlearn.fragments.quiz.gameover.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentGameOverBinding
import ru.malevichrp.superlearn.fragments.editTest.NavigateToEditTest
import ru.malevichrp.superlearn.fragments.quiz.load.presentation.NavigateToLoad

class GameOverFragment : AbstractFragment.BindingUi<FragmentGameOverBinding>() {
    init {
        Log.d("mlvc", "game over fragment init")
    }
    private lateinit var viewModel: GameOverViewModel

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameOverBinding =
        FragmentGameOverBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mlvc", "game over fragment onCreate")

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(GameOverViewModel::class.java)

        binding.newGameButton.setOnClickListener {
            viewModel.clear()
            (requireActivity() as GoBack).goBack()
        }
        val uiState = viewModel.init(savedInstanceState == null)
        binding.statsTextView.update(uiState)
    }
}

abstract class Logged : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Mlvc", "Fragment: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Mlvc", "Fragment: onCreated")
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Mlvc", "Fragment: onViewCreated")
    }
}