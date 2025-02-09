package com.example.ishopping.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeShoppingItemViewmodel @Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _items = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val items = _items.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getBookmarkShoppingItems(),
                _items
            ) { bookmarkedItems, shoppingItems ->
                val bookmarkedIds = bookmarkedItems.map { it.id }.toSet()
                shoppingItems.map { it.copy(isBookmarked = it.id in bookmarkedIds) }
            }.collect { updatedItems ->
                _items.value = updatedItems
            }
        }
    }

    fun loadShoppingItems(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val shoppingItems = repository.getShoppingItems(query)
            _items.value = shoppingItems
            _isLoading.value = false
        }
    }

    fun onBookmarkButtonClick(shoppingItem: ShoppingItem) {
        if (shoppingItem.isBookmarked) {
            removeBookmark(shoppingItem)
        } else {
            addBookmark(shoppingItem)
        }
    }

    private fun addBookmark(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBookmarkItem(shoppingItem.copy(isBookmarked = true))
        }
    }

    private fun removeBookmark(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmarkItem(shoppingItem)
        }
    }
}
