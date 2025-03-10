package com.example.ishopping.di

import android.content.Context
import androidx.room.Room
import com.example.ishopping.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            "ishopping-db"
        ).build()
    }

    @Provides
    fun provideBookmarkItemDao(appDatabase: AppDatabase) = appDatabase.shoppingItemDao()
}