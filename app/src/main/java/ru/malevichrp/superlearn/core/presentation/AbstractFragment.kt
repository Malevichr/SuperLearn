package ru.malevichrp.superlearn.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

interface AbstractFragment {
    abstract class BindingUi<B : ViewBinding> : Fragment() {
        private var _binding: B? = null
        protected val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = inflate(inflater, container)
            val view = binding.root
            return view
        }

        protected abstract fun inflate(
            inflater: LayoutInflater,
            container: ViewGroup?
        ): B

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

    abstract class Async<U : UiState, V : MyViewModel.Async<U>, B : ViewBinding> : BindingUi<B>() {
        protected lateinit var viewModel: V
        protected abstract val update: (U) -> Unit

        override fun onResume() {
            super.onResume()
            viewModel.startUpdates(observer = update)
        }

        override fun onPause() {
            super.onPause()
            viewModel.stopUpdates()
        }
    }
}