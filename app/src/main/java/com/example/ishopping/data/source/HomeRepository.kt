package com.example.ishopping.data.source

import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.data.source.remote.ShoppingService

class HomeRepository(private val shoppingService: ShoppingService) {
    suspend fun getShoppingItems(query: String): List<ShoppingItem> {
        return shoppingService.getShoppingItems(query).items
    }
}