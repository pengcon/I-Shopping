package com.example.ishopping.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.ItemSearchShoppingItemBinding
import com.example.ishopping.util.BookmarkClickListener

class HomeShoppingItemAdapter(private val listener: BookmarkClickListener) :
    RecyclerView.Adapter<HomeShoppingItemAdapter.ShoppingItemViewHolder>() {

    private val homeItems = mutableListOf<ShoppingItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingItemViewHolder {
        return ShoppingItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return homeItems.size
    }

    override fun onBindViewHolder(
        holder: ShoppingItemViewHolder,
        position: Int
    ) {
        holder.bind(homeItems[position], listener)
    }

    fun addItems(items: List<ShoppingItem>) {
        val startPosition = items.size
        homeItems.addAll(items)
        notifyItemRangeInserted(startPosition, items.size)
    }

    class ShoppingItemViewHolder(private val binding: ItemSearchShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shoppingItem: ShoppingItem, listener: BookmarkClickListener) {
            binding.shoppingItem = shoppingItem
            binding.listener = listener
        }

        companion object {
            fun from(parent: ViewGroup): ShoppingItemViewHolder {
                val binding = ItemSearchShoppingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ShoppingItemViewHolder(binding)
            }
        }
    }
}
