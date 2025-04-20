package ru.malevichrp.superlearn.fragments.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentThemeBinding

class ThemeFragment : AbstractFragment.BindingUi<FragmentThemeBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentThemeBinding =
        FragmentThemeBinding.inflate(layoutInflater, container, false)
}
