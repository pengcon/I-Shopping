package com.example.ishopping.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewmodel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        startSearchFlow()
    }

    fun updateSearchText(query: String) {
        _searchText.value = query
    }

    private fun startSearchFlow() {
        searchText
            .debounce(500L)
            .filter { it.isNotEmpty() }
            .onEach { query ->
                // TODO: api 호출
            }
            .launchIn(viewModelScope)
    }
}
