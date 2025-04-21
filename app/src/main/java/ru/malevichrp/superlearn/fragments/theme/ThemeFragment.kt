package ru.malevichrp.superlearn.fragments.theme

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.core.presentation.Navigate
import ru.malevichrp.superlearn.databinding.FragmentThemeBinding
import ru.malevichrp.superlearn.fragments.editTest.NavigateToEditTest
import ru.malevichrp.superlearn.fragments.theory.NavigateToTheory

class ThemeFragment : AbstractFragment.BindingUi<FragmentThemeBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentThemeBinding =
        FragmentThemeBinding.inflate(layoutInflater, container, false)

    private lateinit var viewModel: ThemeViewModel

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            viewModel.changeThemeName(text = binding.inputEditText.text.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(ThemeViewModel::class.java)
        binding.backButton.setOnClickListener {
            (requireActivity() as GoBack).goBack()
        }
        binding.deleteThemeButton.setOnClickListener {
            viewModel.deleteCurrentTheme()
            (requireActivity() as GoBack).goBack()
        }
        binding.testButton.setOnClickListener {
            if(viewModel.isEdit()){
                (requireActivity() as NavigateToEditTest).navigateToEditTest()
            } else
                (requireActivity() as Navigate).navigateToQuiz()

        }
        binding.theoryButton.setOnClickListener {
            (requireActivity() as Navigate).navigateToTheory()
        }
        if (savedInstanceState == null) {
            viewModel.createNewTheme()
            binding.inputEditText.setText(viewModel.themeText())
        }
    }

    override fun onResume() {
        super.onResume()
        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }
}
