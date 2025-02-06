package com.example.ishopping.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.ishopping.data.model.Item
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
    ViewModel(){

    private val _bookMarkedItems = MutableStateFlow<Set<String>>(emptySet())
    val bookmarkItems = _bookMarkedItems.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        viewModelScope.launch {
            searchRepository.getBookmarkShoppingItems().collect { items ->
                _bookMarkedItems.value = items.map { it.id }.toSet()
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val items: Flow<PagingData<Item>> =
        _searchText
            .debounce(500L)
            .filter { it.isNotEmpty() }
            .flatMapLatest { query ->
                searchRepository.getShoppingItems(query)
            }
            .cachedIn(viewModelScope)

    val shoppingItems: Flow<PagingData<ShoppingItem>> = items
        .combine(bookmarkItems) { pagingData, bookmarkedIds ->
            pagingData.map { item ->
                val isBookmarked = bookmarkedIds.contains(item.productId)
                ShoppingItem(
                    id = item.productId,
                    item = item,
                    isBookmarked = isBookmarked
                )
            }
        }
        .cachedIn(viewModelScope)


    fun onBookmarkButtonClick(shoppingItem: ShoppingItem)  {
        if (shoppingItem.isBookmarked) {
            removeBookmark(shoppingItem)
        } else {
            addBookmark(shoppingItem)
        }
    }

    fun addBookmark(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.insertBookmarkItem(shoppingItem.copy(isBookmarked = true))
        }
    }

    fun removeBookmark(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.deleteBookmarkItem(shoppingItem)
        }
    }

    fun updateSearchText(query: String) {
        _searchText.value = query
    }
}