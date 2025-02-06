package com.example.ishopping.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.util.converters.ShoppingItemTypeConverter

@Database(entities = [ShoppingItem::class], version = 2)
@TypeConverters(ShoppingItemTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkItemDao(): BookmarkItemDao

}
