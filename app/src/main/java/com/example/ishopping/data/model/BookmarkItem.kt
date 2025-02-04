package com.example.ishopping.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_items")
data class BookmarkItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val link: String,
    val image: String,
    val lowPrice: String,
    val productId : String
) {

}
