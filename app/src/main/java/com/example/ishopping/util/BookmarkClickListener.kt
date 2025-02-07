package com.example.ishopping.util

import com.example.ishopping.data.model.ShoppingItem

fun interface BookmarkClickListener {
    fun onBookmarkButtonClick(shoppingItem: ShoppingItem)
}