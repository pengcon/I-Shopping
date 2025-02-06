package com.example.ishopping.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.data.model.Item
import com.example.ishopping.databinding.ItemShoppingItemBinding

class HomeShoppingItemAdapter : RecyclerView.Adapter<HomeShoppingItemAdapter.ShoppingItemViewHolder>() {

    private val items = mutableListOf<Item>()

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

    fun addItems(items: List<Item>) {
        val startPosition = this@HomeShoppingItemAdapter.items.size
        this@HomeShoppingItemAdapter.items.addAll(items)
        notifyItemRangeInserted(startPosition, items.size)
    }

    class ShoppingItemViewHolder(private val binding: ItemShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.shoppingItem = item
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
