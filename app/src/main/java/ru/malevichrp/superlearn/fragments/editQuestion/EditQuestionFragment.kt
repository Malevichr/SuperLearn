package ru.malevichrp.superlearn.fragments.editQuestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.R
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentEditQuestionBinding

class EditQuestionFragment : AbstractFragment.BindingUi<FragmentEditQuestionBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditQuestionBinding =
        FragmentEditQuestionBinding.inflate(inflater, container, false)
    private var rightAnswerIndex = 0
    val radioButtonIds = listOf(R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4)

    val update: (Question) -> Unit = { question: Question ->
        binding.editQuestionInput.setText(question.title)
        binding.firstAnswerInput.setText(question.answer1)
        binding.secondAnswerInput.setText(question.answer2)
        binding.thirdAnswerInput.setText(question.answer3)
        binding.fourthAnswerInput.setText(question.answer4)
        binding.radioGroup.check(radioButtonIds.get(question.rightAnswerIndex))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = (requireActivity() as ProvideViewModel).provideViewModel(EditQuestionViewModel::class.java)
        val saveAction = {
            val question = Question(
                binding.editQuestionInput.text.toString(),
                binding.firstAnswerInput.text.toString(),
                binding.secondAnswerInput.text.toString(),
                binding.thirdAnswerInput.text.toString(),
                binding.fourthAnswerInput.text.toString(),
                rightAnswerIndex
            )
            viewModel.saveQuestion(
                question = question
            )
        }
        binding.backButton.setOnClickListener {
            saveAction()
            (requireActivity() as GoBack).goBack()
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            rightAnswerIndex =  when(checkedId){
                R.id.radio1 -> 0
                R.id.radio2 -> 1
                R.id.radio3 -> 2
                else -> 3
            }
        }
        binding.saveButton.setOnClickListener {
            saveAction()
            viewModel.addNewQuestion(callback = update)
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteQuestion()
            (requireActivity() as GoBack).goBack()
        }


        viewModel.initQuestion(callback = update)
    }
}