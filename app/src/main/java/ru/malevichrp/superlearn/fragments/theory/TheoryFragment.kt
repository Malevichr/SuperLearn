package ru.malevichrp.superlearn.fragments.theory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentTheoryBinding

class TheoryFragment : AbstractFragment.BindingUi<FragmentTheoryBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentTheoryBinding =
        FragmentTheoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            (requireActivity() as GoBack).goBack()
        }
    }
}