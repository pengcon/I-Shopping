package com.example.ishopping.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.ishopping.data.model.BookmarkItem
import com.example.ishopping.data.model.ItemUiModel
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewmodel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val _bookMarkedItems = MutableStateFlow<Set<String>>(emptySet())
    val bookmarkItems = _bookMarkedItems.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val shoppingItems: Flow<PagingData<ShoppingItem>> =
        _searchText
            .debounce(500L)
            .filter { it.isNotEmpty() }
            .flatMapLatest { query ->
                searchRepository.getShoppingItems(query)
            }
            .cachedIn(viewModelScope)

    val itemUiModel: Flow<PagingData<ItemUiModel>> = shoppingItems
        .combine(bookmarkItems) { pagingData, bookmarkedIds ->
            pagingData.map { shoppingItem ->
                val isBookmarked = bookmarkedIds.contains(shoppingItem.productId)
                ItemUiModel(
                    item = shoppingItem,
                    isBookmarked =  isBookmarked,
                    onBookmark = {
                        toggleBookmark(shoppingItem,isBookmarked)
                    }
                )
            }
        }
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            searchRepository.getBookmarkShoppingItems().collect { items ->
                _bookMarkedItems.value = items.map { it.productId }.toSet()
            }
        }
    }

    fun toggleBookmark(shoppingItem: ShoppingItem, isBookmarked: Boolean) {
        if (isBookmarked) {
            removeBookmark(shoppingItem)
        } else {
            addBookmark(shoppingItem)
        }
    }

    fun addBookmark(item: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val bookmarkItem = BookmarkItem(
                title = item.title,
                link = item.link,
                image = item.image,
                lowPrice = item.lowPrice,
                productId = item.productId
            )
            searchRepository.insertBookmarkItem(bookmarkItem)
        }
    }

    fun removeBookmark(item: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val bookmarkItem = BookmarkItem(
                title = item.title,
                link = item.link,
                image = item.image,
                lowPrice = item.lowPrice,
                productId = item.productId
            )
            Log.d("removeBookmark", "removeBookmark: $bookmarkItem")
            searchRepository.deleteBookmarkItem(bookmarkItem)
        }
    }

    fun updateSearchText(query: String) {
        _searchText.value = query
    }
}