package com.example.ishopping.util.comparator

import androidx.recyclerview.widget.DiffUtil
import com.example.ishopping.data.model.ShoppingItem

object ShoppingItemComparator : DiffUtil.ItemCallback<ShoppingItem>() {
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        // Id is unique.
        return oldItem.item.productId == newItem.item.productId
    }

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem == newItem
    }
}