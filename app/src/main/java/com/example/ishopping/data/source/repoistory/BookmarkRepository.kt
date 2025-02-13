package com.example.ishopping.data.source.repoistory

import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.local.ShoppingItemDao
import javax.inject.Inject

class BookmarkRepository @Inject constructor(private val dao: ShoppingItemDao) {
    fun getAllBookmarkItems() = dao.getAll()
    fun deleteBookmarkItem(item: ShoppingItem) = dao.delete(item)
}
