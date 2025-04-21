package ru.malevichrp.superlearn.views.recycler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.malevichrp.superlearn.databinding.ItemTextBinding

class TextAdapter(
    private val clickAction: (String) -> Unit
) : RecyclerView.Adapter<TextViewHolder>() {
    val textItems = arrayListOf<CharSequence>()

    fun addAll(textItems: ArrayList<CharSequence>) {
        this.textItems.clear()
        this.textItems.addAll(textItems)
        notifyItemRangeChanged(0, textItems.size+1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder =
        TextViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false), clickAction)

    override fun getItemCount(): Int = textItems.size


    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(textItems[position])
    }
    private inner class DiffUtilCallback(
        private val newList: ArrayList<CharSequence>,
    ) : DiffUtil.Callback() {

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
            return true
        }

        override fun getOldListSize(): Int {
            return textItems.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return textItems[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return textItems[oldItemPosition] == newList[newItemPosition]
        }

    }
}

class TextViewHolder(
    private val binding: ItemTextBinding,
    private val clickAction: (String) -> Unit
) : ViewHolder(binding.root) {
    fun bind(source: CharSequence) {
        binding.textItem.text = source
        binding.textItem.setOnClickListener {
            clickAction.invoke(source.toString())
        }
    }
}
