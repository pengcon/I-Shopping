package com.example.ishopping.data.source

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.local.BookmarkItemDao
import com.example.ishopping.data.model.Item
import com.example.ishopping.data.source.remote.ShoppingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val shoppingService: ShoppingService,
    private val dao: BookmarkItemDao
) {
    fun getShoppingItems(query: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = {
                SearchPagingSource(shoppingService, query)
            }
        ).flow
    }

     fun getBookmarkShoppingItems(): Flow<List<ShoppingItem>> {
        return dao.getAll()
    }

     fun insertBookmarkItem(item: ShoppingItem) {
        dao.insert(item)
    }

     fun deleteBookmarkItem(item: ShoppingItem) {
        Log.d("deleteBookmarkItem", "deleteBookmarkItem: $item")
        dao.delete(item)
    }
    fun deleteAllBookmarkItems() {
        dao.deleteAllBookmarkItems()
    }
}
