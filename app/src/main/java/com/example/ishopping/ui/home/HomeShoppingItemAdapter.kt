package com.example.ishopping.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.ItemSearchShoppingItemBinding
import com.example.ishopping.util.BookmarkClickListener

class HomeShoppingItemAdapter(
    diffCallback: DiffUtil.ItemCallback<ShoppingItem>,
    private val listener: BookmarkClickListener
) : ListAdapter<ShoppingItem, HomeShoppingItemViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeShoppingItemViewHolder {
        return HomeShoppingItemViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: HomeShoppingItemViewHolder, position: Int) {
        val shoppingItem = getItem(position)
        shoppingItem?.let { item ->
            holder.bind(item)
        } ?: run {
            holder.bind(ShoppingItem.placeholder())
        }
    }
}

class HomeShoppingItemViewHolder(
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
        ): HomeShoppingItemViewHolder {
            val binding = ItemSearchShoppingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return HomeShoppingItemViewHolder(binding, listener)
        }
    }
}

