package com.example.ishopping.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_items")
data class BookmarkItem(
    @PrimaryKey val productId: String,
    val title: String,
    val link: String,
    val image: String,
    val lowPrice: String,
)