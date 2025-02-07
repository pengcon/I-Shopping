package com.example.ishopping.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeShoppingItemViewmodel @Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    private val _items = MutableLiveData<List<ShoppingItem>>()
    val items: LiveData<List<ShoppingItem>> = _items

    fun loadShoppingItems(query: String) {
        viewModelScope.launch {
            val shoppingItems = repository.getShoppingItems(query)
            _items.value = shoppingItems
        }
    }

    fun onBookmarkButtonClick(shoppingItem: ShoppingItem)  {
        if (shoppingItem.isBookmarked) {
            removeBookmark(shoppingItem)
        } else {
            addBookmark(shoppingItem)
        }
    }

    fun addBookmark(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBookmarkItem(shoppingItem.copy(isBookmarked = true))
        }
    }

    fun removeBookmark(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmarkItem(shoppingItem)
        }
    }
}