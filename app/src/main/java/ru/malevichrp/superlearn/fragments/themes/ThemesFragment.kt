package ru.malevichrp.superlearn.fragments.themes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentThemesBinding
import ru.malevichrp.superlearn.fragments.theme.NavigateToTheme
import ru.malevichrp.superlearn.views.recycler.TextAdapter

class ThemesFragment :
    AbstractFragment.Async<ThemesUiState, ThemesViewModel, FragmentThemesBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentThemesBinding =
        FragmentThemesBinding.inflate(inflater, container, false)

    private var textAdapter: TextAdapter = TextAdapter { themeText ->
        viewModel.navigateToTheme(themeText)
    }
    override val update: (ThemesUiState) -> Unit = { uiState: ThemesUiState ->
        uiState.update(
            binding.addThemeButton,
            textAdapter
        )
        uiState.navigate(requireActivity() as NavigateToTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(ThemesViewModel::class.java)

        binding.addThemeButton.setOnClickListener {
            viewModel.createTheme()
        }
        binding.themesRecycler.adapter = textAdapter
        viewModel.loadThemes(savedInstanceState == null)
    }
}