package com.example.ishopping.data.source.repoistory

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ishopping.data.model.Item
import com.example.ishopping.data.source.remote.ShoppingService

class SearchPagingSource(
    private val service: ShoppingService,
    private val query: String
) : PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        try {
            val nextPageNumber = params.key ?: 1
            val startItemNumber = ((nextPageNumber - 1) * params.loadSize) + 1
            val response = service.getShoppingItems(
                query,
                start = startItemNumber
            )
            val isLastPage = response.items.size < params.loadSize

            return LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = if (isLastPage) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
