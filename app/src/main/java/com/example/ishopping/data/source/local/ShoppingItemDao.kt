package com.example.ishopping.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ishopping.data.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Insert
    suspend fun insert(shoppingItem: ShoppingItem)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM bookmark_items")
    fun getAll(): Flow<List<ShoppingItem>>

    @Query("DELETE FROM bookmark_items")
    suspend fun deleteAllBookmarkItems()
}