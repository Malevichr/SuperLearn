package ru.malevichrp.superlearn.fragments.theory.theoryRecycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TheoryRecyclerAdapter(private val clickActions: TheoryClickActions.Mutable) :
    RecyclerView.Adapter<TheoryViewHolder>() {
    private val typeList: List<TheoryRecyclerType> = listOf(
        TheoryRecyclerType.Text,
        TheoryRecyclerType.Image
    )
    private val recyclerItems = mutableListOf<TheoryRecyclerItem>()
    fun update(newItems: List<TheoryRecyclerItem>) {
        val callback = DiffUtilCallback(newItems)
        val diff = DiffUtil.calculateDiff(callback)
        recyclerItems.clear()
        recyclerItems.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        val type = recyclerItems[position].type()
        return typeList.indexOf(type)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TheoryViewHolder {
        return typeList[viewType].viewHolder(parent, clickActions)
    }

    override fun getItemCount(): Int {
        return recyclerItems.size
    }

    override fun onBindViewHolder(holder: TheoryViewHolder, position: Int) {
        holder.bind(recyclerItem = recyclerItems[position], clickActions)
    }

    private inner class DiffUtilCallback(
        private val newList: List<TheoryRecyclerItem>,
    ) : DiffUtil.Callback() {

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
            return true
        }

        override fun getOldListSize(): Int {
            return recyclerItems.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return recyclerItems[oldItemPosition].id() == newList[newItemPosition].id()
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return recyclerItems[oldItemPosition] == newList[newItemPosition]
        }

    }
}


