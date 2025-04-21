package ru.malevichrp.superlearn.fragments.editTest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentEditTestBinding

class EditTestFragment : AbstractFragment.BindingUi<FragmentEditTestBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentEditTestBinding =
        FragmentEditTestBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            (requireActivity() as GoBack).goBack()
        }
    }
}