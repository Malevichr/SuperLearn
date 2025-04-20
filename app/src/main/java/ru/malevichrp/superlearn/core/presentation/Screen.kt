package ru.malevichrp.superlearn.core.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    object Empty : Screen {
        override fun show(containerId: Int, fragmentManager: FragmentManager) {}
    }

    fun show(containerId: Int, fragmentManager: FragmentManager)

    abstract class Replace(private val fragment: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId, makeFragment())
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
                .commit()
        }

        protected open fun makeFragment(): Fragment =
            fragment.getDeclaredConstructor().newInstance()
    }
}