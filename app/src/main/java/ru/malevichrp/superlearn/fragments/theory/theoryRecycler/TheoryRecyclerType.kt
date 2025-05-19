package ru.malevichrp.superlearn.fragments.theory.theoryRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.malevichrp.superlearn.databinding.ItemImageBinding
import ru.malevichrp.superlearn.databinding.ItemInputLongTextBinding

interface TheoryRecyclerType {
    fun viewHolder(
        parent: ViewGroup,
        clickActions: TheoryClickActions.Mutable
    ): TheoryViewHolder

    object Text : TheoryRecyclerType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: TheoryClickActions.Mutable
        ): TheoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemInputLongTextBinding.inflate(inflater, parent, false)
            return TheoryViewHolder.Text(binding)
        }
    }

    object Image : TheoryRecyclerType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: TheoryClickActions.Mutable
        ): TheoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemImageBinding.inflate(inflater, parent, false)
            return TheoryViewHolder.Image(binding)
        }
    }
}