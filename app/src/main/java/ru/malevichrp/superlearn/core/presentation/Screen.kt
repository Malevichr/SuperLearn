package ru.malevichrp.superlearn.core.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun show(containerId: Int, fragmentManager: FragmentManager)

    abstract class Replace(private val fragment: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId, makeFragment())
                .setReorderingAllowed(true)
                .commit()
        }

        protected open fun makeFragment(): Fragment =
            fragment.getDeclaredConstructor().newInstance()
    }

    abstract class ReplaceWithBackStack(
        private val fragment: Class<out Fragment>,
        private val name: String
    ) : Screen {
        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId, makeFragment())
                .addToBackStack(name)
                .setReorderingAllowed(true)
                .commit()
        }

        protected open fun makeFragment(): Fragment =
            fragment.getDeclaredConstructor().newInstance()
    }
}