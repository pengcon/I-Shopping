package com.example.ishopping.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkItemDao {
    @Insert
    fun insert(bookmarkItem: BookmarkItem)

    @Delete
    fun delete(bookmarkItem: BookmarkItem)

    @Query("SELECT * FROM bookmark_items")
    fun getAll(): List<BookmarkItem>
}
