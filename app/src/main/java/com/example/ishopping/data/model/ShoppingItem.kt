package com.example.ishopping.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_items")
data class ShoppingItem(
    @PrimaryKey val id: String,
    val item: Item,
    val isBookmarked: Boolean
) {
    companion object {
        fun placeholder(): ShoppingItem {
            return ShoppingItem(
                id = "",
                item = Item.placeholder(),
                isBookmarked = false
            )
        }
    }
}