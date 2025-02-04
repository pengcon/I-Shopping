package com.example.ishopping.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ishopping.data.model.BookmarkItem
import com.example.ishopping.data.model.BookmarkItemDao

@Database(entities = [BookmarkItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkItemDao(): BookmarkItemDao
}
