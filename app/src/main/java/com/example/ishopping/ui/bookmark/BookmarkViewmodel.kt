package com.example.ishopping.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.repoistory.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewmodel @Inject constructor(private val bookmarkRepository: BookmarkRepository) :
    ViewModel() {

    private val _bookmarkedItems = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val bookmarkedItems = _bookmarkedItems.asStateFlow()

    fun loadBookmarkedItems() {
        viewModelScope.launch {
            bookmarkRepository.getAllBookmarkItems().collect { items ->
                _bookmarkedItems.value = items
            }
        }
    }

    fun bookmarkButtonClick(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.deleteBookmarkItem(shoppingItem)
        }
    }
}
