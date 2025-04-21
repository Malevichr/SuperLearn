package ru.malevichrp.superlearn.fragments.editQuestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentEditQuestionBinding

class EditQuestionFragment : AbstractFragment.BindingUi<FragmentEditQuestionBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditQuestionBinding =
        FragmentEditQuestionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            (requireActivity() as GoBack).goBack()
        }
    }
}