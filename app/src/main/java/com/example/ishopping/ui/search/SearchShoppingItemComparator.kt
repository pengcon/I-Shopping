package com.example.ishopping.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.example.ishopping.data.model.ShoppingItem

object SearchShoppingItemComparator : DiffUtil.ItemCallback<ShoppingItem>() {
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        // Id is unique.
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem == newItem
    }
}

