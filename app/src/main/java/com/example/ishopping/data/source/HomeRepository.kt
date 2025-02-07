package com.example.ishopping.data.source

import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.model.toShoppingItem
import com.example.ishopping.data.source.local.ShoppingItemDao
import com.example.ishopping.data.source.remote.ShoppingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val shoppingService: ShoppingService,
    private val dao: ShoppingItemDao
) {
    suspend fun getShoppingItems(query: String): List<ShoppingItem> {
        return shoppingService.getShoppingItems(query).items.map {
            it.toShoppingItem()
        }
    }
    fun getBookmarkShoppingItems(): Flow<List<ShoppingItem>> {
        return dao.getAll()
    }

    fun insertBookmarkItem(item: ShoppingItem) {
        dao.insert(item)
    }

    fun deleteBookmarkItem(item: ShoppingItem) {
        dao.delete(item)
    }
}