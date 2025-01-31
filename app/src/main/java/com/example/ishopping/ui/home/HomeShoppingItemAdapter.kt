package com.example.ishopping.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.ItemShoppingItemBinding

class HomeShoppingItemAdapter : RecyclerView.Adapter<HomeShoppingItemAdapter.ShoppingItemViewHolder>() {

    private val items = mutableListOf<ShoppingItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingItemViewHolder {
        return ShoppingItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ShoppingItemViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    fun addItems(shoppingItems: List<ShoppingItem>) {
        val startPosition = items.size
        items.addAll(shoppingItems)
        notifyItemRangeInserted(startPosition, shoppingItems.size)
    }

    class ShoppingItemViewHolder(private val binding: ItemShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shoppingItem: ShoppingItem) {
            binding.shoppingItem = shoppingItem
        }

        companion object {
            fun from(parent: ViewGroup): ShoppingItemViewHolder {
                val binding = ItemShoppingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ShoppingItemViewHolder(binding)
            }
        }
    }
}
