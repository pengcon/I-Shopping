package com.example.ishopping.data.model

data class ItemUiModel(
    val item: ShoppingItem,
    val isBookmarked: Boolean,
    val onBookmark: () -> Unit
) {
    companion object {
        fun placeholder() = ItemUiModel(
            item = ShoppingItem.placeholder(),
            isBookmarked = false,
            onBookmark = {}
        )
    }
}