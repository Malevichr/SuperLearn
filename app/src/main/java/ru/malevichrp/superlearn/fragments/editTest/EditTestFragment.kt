package ru.malevichrp.superlearn.fragments.editTest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.core.presentation.Navigate
import ru.malevichrp.superlearn.databinding.FragmentEditTestBinding
import ru.malevichrp.superlearn.fragments.editQuestion.NavigateToEditQuestion
import ru.malevichrp.superlearn.views.recycler.TextAdapter

class EditTestFragment :
    AbstractFragment.Async<EditTestUiState, EditTestViewModel, FragmentEditTestBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentEditTestBinding =
        FragmentEditTestBinding.inflate(inflater, container, false)

    private lateinit var adapter: TextAdapter
    override val update: (EditTestUiState) -> Unit = { uiState ->
        uiState.update(adapter = adapter, visibilityButton = binding.startTestButton)
        uiState.navigate(requireActivity() as NavigateToEditQuestion)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(EditTestViewModel::class.java)
        adapter = TextAdapter { questionId ->
            viewModel.navigateToTargetQuestion(questionId)
        }
        binding.testsRecycler.adapter = adapter
        binding.backButton.setOnClickListener {
            (requireActivity() as GoBack).goBack()
        }
        binding.addQuestionButton.setOnClickListener {
            viewModel.addQuestion()
        }
        binding.startTestButton.setOnClickListener {
            (requireActivity() as Navigate).navigateToGame()
        }
        viewModel.loadThemes()
    }
}