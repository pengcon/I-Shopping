package com.example.ishopping.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.ItemSearchShoppingItemBinding
import com.example.ishopping.ui.search.SearchShoppingItemAdapter.SearchShoppingItemViewHolder

class SearchShoppingItemAdapter(diffCallback: DiffUtil.ItemCallback<ShoppingItem>) :
    PagingDataAdapter<ShoppingItem, SearchShoppingItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchShoppingItemViewHolder {
        return SearchShoppingItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: SearchShoppingItemViewHolder,
        position: Int
    ) {
        val shoppingItem = getItem(position)
        shoppingItem?.let { item ->
            holder.bind(item)
        } ?: run {
            holder.bind(ShoppingItem.placeholder())
        }
    }

    class SearchShoppingItemViewHolder(private val binding: ItemSearchShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shoppingItem: ShoppingItem) {
            binding.shoppingItem = shoppingItem
        }

        companion object {
            fun from(parent: ViewGroup): SearchShoppingItemViewHolder {
                val binding = ItemSearchShoppingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return SearchShoppingItemViewHolder(binding)
            }
        }
    }
}
