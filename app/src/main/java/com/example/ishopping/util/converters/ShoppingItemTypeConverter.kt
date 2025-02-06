package com.example.ishopping.util.converters

import androidx.room.TypeConverter
import com.example.ishopping.data.model.Item
import com.google.gson.Gson

class ShoppingItemTypeConverter {
    @TypeConverter
    fun itemToJson(value: Item): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToItem(value: String): Item {
        return Gson().fromJson(value, Item::class.java)
    }
}