package ru.malevichrp.superlearn.fragments.theory

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import ru.malevichrp.superlearn.GoBack
import ru.malevichrp.superlearn.core.di.ProvideViewModel
import ru.malevichrp.superlearn.core.presentation.AbstractFragment
import ru.malevichrp.superlearn.databinding.FragmentTheoryBinding
import ru.malevichrp.superlearn.fragments.theory.theoryRecycler.TheoryClickActions
import ru.malevichrp.superlearn.fragments.theory.theoryRecycler.TheoryRecyclerAdapter

class TheoryFragment() :
    AbstractFragment.Async<TheoryUiState, TheoryViewModel, FragmentTheoryBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentTheoryBinding =
        FragmentTheoryBinding.inflate(inflater, container, false)

    override val update: (TheoryUiState) -> Unit = { uiState ->
        uiState.show(binding.addMediaButton, adapter)
    }


    private val adapter = TheoryRecyclerAdapter(object : TheoryClickActions.Mutable {
        override fun hold(id: Int) {
            viewModel.deleteImage(id = id)
        }

        override fun inputText(id: Int, text: String) {
            viewModel.inputText(id = id, text = text)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            (requireActivity() as GoBack).goBack()
        }
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(TheoryViewModel::class.java)
        binding.theoryRecycler.adapter = adapter

        binding.addMediaButton.setOnClickListener {
            checkPermissionAndPickImage()
        }
        binding.backButton.setOnClickListener {
            (requireActivity() as GoBack).goBack()
        }
        viewModel.init(isFirstRun = savedInstanceState == null)
    }

    private fun checkPermissionAndPickImage() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(
                requireActivity(), permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            pickImageLauncher.launch("image/*")
        } else {
            requestPermissionLauncher.launch(permission)
        }
    }

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            requireActivity().contentResolver.takePersistableUriPermission(
                uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            viewModel.addImage(uri = it)
        }
    }
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) pickImageLauncher.launch("image/*")
    }
}