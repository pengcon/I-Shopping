package com.example.ishopping.data.source

import com.example.ishopping.data.model.Item
import com.example.ishopping.data.source.remote.ShoppingService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val shoppingService: ShoppingService) {
    suspend fun getShoppingItems(query: String): List<Item> {
        return shoppingService.getShoppingItems(query).items
    }

}