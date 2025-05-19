package ru.malevichrp.superlearn.fragments.theory.theoryRecycler

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ru.malevichrp.superlearn.databinding.ItemImageBinding
import ru.malevichrp.superlearn.databinding.ItemInputLongTextBinding

abstract class TheoryViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    open fun bind(recyclerItem: TheoryRecyclerItem, actions: TheoryClickActions.Mutable) = Unit

    class Text(private val binding: ItemInputLongTextBinding) : TheoryViewHolder(binding.root) {
        override fun bind(recyclerItem: TheoryRecyclerItem, actions: TheoryClickActions.Mutable) {
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                    Unit

                override fun afterTextChanged(s: Editable?) {
                    actions.inputText(recyclerItem.id(), s.toString())
                }
            }
            binding.input.setText(recyclerItem.text())
            binding.input.addTextChangedListener(textWatcher)
        }
    }

    class Image(private val binding: ItemImageBinding) : TheoryViewHolder(binding.root) {
        override fun bind(recyclerItem: TheoryRecyclerItem, actions: TheoryClickActions.Mutable) {
            Picasso.get()
                .load(recyclerItem.imageUri())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(binding.imageItem)
            binding.imageItem.setOnLongClickListener {
                actions.hold(recyclerItem.id())
                Log.d("mlvc", "Holded")
                true
            }
        }
    }
}
