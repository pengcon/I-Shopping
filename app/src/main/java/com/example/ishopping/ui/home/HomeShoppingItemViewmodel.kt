package com.example.ishopping.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
}