package ru.malevichrp.superlearn.views.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.malevichrp.superlearn.databinding.ItemTextBinding

class TextAdapter(
    private val clickAction: (Int) -> Unit
) : RecyclerView.Adapter<TextViewHolder>() {
    var items = TextItems()

    fun addAll(
        newItems: TextItems
    ) {
        this.items = newItems

        notifyItemRangeChanged(0, newItems.textTitles.size+1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder =
        TextViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false), clickAction)

    override fun getItemCount(): Int = items.textTitles.size


    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(items.textTitles[position], items.itemIds[position])
    }
}

class TextItems(
    val textTitles: ArrayList<CharSequence> = arrayListOf(),
    val itemIds: ArrayList<Int> = arrayListOf()
){
    private var idCounter = 0
    fun textById(id: Int): CharSequence =
        textTitles[itemIds.indexOf(id)]

    fun add(text: CharSequence) : TextItems{
        idCounter++
        textTitles.add(text)
        itemIds.add(idCounter)
        return this
    }
    fun lastAddedId() = idCounter

    fun remove(id: Int){
        val idInner = itemIds.indexOf(id)
        textTitles.removeAt(idInner)
        itemIds.removeAt(idInner)
    }
}

class TextViewHolder(
    private val binding: ItemTextBinding,
    private val clickAction: (Int) -> Unit
) : ViewHolder(binding.root) {
    fun bind(source: CharSequence, id: Int) {
        binding.textItem.text = source
        binding.textItem.setOnClickListener {
            clickAction.invoke(id)
        }
    }
}
