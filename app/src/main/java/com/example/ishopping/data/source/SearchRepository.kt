package com.example.ishopping.data.source

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ishopping.data.model.BookmarkItem
import com.example.ishopping.data.source.local.BookmarkItemDao
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.remote.ShoppingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val shoppingService: ShoppingService,
    private val dao: BookmarkItemDao
) {
    fun getShoppingItems(query: String): Flow<PagingData<ShoppingItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = {
                SearchPagingSource(shoppingService, query)
            }
        ).flow
    }

     fun getBookmarkShoppingItems(): Flow<List<BookmarkItem>> {
        return dao.getAll()
    }

     fun insertBookmarkItem(item: BookmarkItem) {
        dao.insert(item)
    }

     fun deleteBookmarkItem(item: BookmarkItem) {
        Log.d("deleteBookmarkItem", "deleteBookmarkItem: $item")
        dao.delete(item)
    }
}
