package ru.malevichrp.superlearn.views.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
        notifyItemRangeChanged(0, this.textItems.size)
    }

    fun save(bundle: Bundle) {
        bundle.putCharSequenceArrayList(KEY, textItems)
    }

    fun restore(bundle: Bundle) {
        textItems.addAll(bundle.getCharSequenceArrayList(KEY) ?: arrayListOf())
        notifyItemRangeChanged(0, textItems.size)
    }

    private companion object {
        private const val KEY = "TextAdapterKey"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder =
        TextViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false), clickAction)

    override fun getItemCount(): Int = textItems.size


    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(textItems[position])
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
