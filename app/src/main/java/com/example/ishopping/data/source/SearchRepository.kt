package com.example.ishopping.data.source

import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.remote.ShoppingService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val shoppingService: ShoppingService) {
    suspend fun getShoppingItems(query: String): List<ShoppingItem> {
        return shoppingService.getShoppingItems(query).items
    }
}
