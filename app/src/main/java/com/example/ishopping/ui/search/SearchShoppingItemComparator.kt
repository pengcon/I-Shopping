package com.example.ishopping.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.example.ishopping.data.model.ItemUiModel
import com.example.ishopping.data.model.ShoppingItem

object SearchShoppingItemComparator : DiffUtil.ItemCallback<ItemUiModel>() {
    override fun areItemsTheSame(oldItem: ItemUiModel, newItem: ItemUiModel): Boolean {
        // Id is unique.
        return oldItem.item.productId == newItem.item.productId
    }

    override fun areContentsTheSame(oldItem: ItemUiModel, newItem: ItemUiModel): Boolean {
        return oldItem == newItem
    }
}

