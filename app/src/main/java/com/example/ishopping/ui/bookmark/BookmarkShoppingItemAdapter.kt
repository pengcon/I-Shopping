package com.example.ishopping.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.ItemSearchShoppingItemBinding
import com.example.ishopping.util.BookmarkClickListener

class BookmarkShoppingItemAdapter(
    diffCallback: DiffUtil.ItemCallback<ShoppingItem>,
    private val listener: BookmarkClickListener
) : ListAdapter<ShoppingItem, BookmarkShoppingItemViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkShoppingItemViewHolder {
        return BookmarkShoppingItemViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: BookmarkShoppingItemViewHolder, position: Int) {
        val shoppingItem = getItem(position)
        shoppingItem?.let { item ->
            holder.bind(item)
        } ?: run {
            holder.bind(ShoppingItem.placeholder())
        }
    }
}

class BookmarkShoppingItemViewHolder(
    private val binding: ItemSearchShoppingItemBinding,
    private val listener: BookmarkClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(shoppingItem: ShoppingItem) {
        binding.shoppingItem = shoppingItem
        binding.listener = listener
    }

    companion object {
        fun from(
            parent: ViewGroup,
            listener: BookmarkClickListener
        ): BookmarkShoppingItemViewHolder {
            val binding = ItemSearchShoppingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookmarkShoppingItemViewHolder(binding, listener)
        }
    }
}

