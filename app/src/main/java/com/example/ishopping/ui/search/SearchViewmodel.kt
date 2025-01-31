package com.example.ishopping.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewmodel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

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

//    init {
//        startSearchFlow()
//    }

    fun updateSearchText(query: String) {
        _searchText.value = query
    }

//    @OptIn(FlowPreview::class)
//    private fun startSearchFlow() {
//        searchText
//            .debounce(500L)
//            .filter { it.isNotEmpty() }
//            .onEach { query ->
//                loadShoppingItems(query)
//            }
//            .launchIn(viewModelScope)
//    }
//    private fun loadShoppingItems(query: String) {
//        viewModelScope.launch {
//            Log.d("SearchViewmodel", "loadShoppingItems: $query")
//            val shoppingItems = searchRepository.getShoppingItems(query)
//            _items.value = shoppingItems
//        }
//    }

}
