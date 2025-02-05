package com.example.ishopping.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ishopping.data.model.BookmarkItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkItemDao {
    @Insert
    fun insert(bookmarkItem: BookmarkItem)

    @Delete
    fun delete(bookmarkItem: BookmarkItem)

    @Query("SELECT * FROM bookmark_items")
    fun getAll(): Flow<List<BookmarkItem>>

    @Query("DELETE FROM bookmark_items")
    suspend fun clearTable()
}