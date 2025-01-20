package com.example.ishopping.data.source.remote

import com.example.ishopping.BuildConfig
import com.example.ishopping.data.model.ShoppingItemsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ShoppingService {

    @Headers(
        "X-Naver-Client-Id: ${BuildConfig.NAVER_CLIENT_ID}",
        "X-Naver-Client-Secret: ${BuildConfig.NAVER_CLIENT_SECRET}"
    )
    @GET("v1/search/shop.json")
    suspend fun getShoppingItems(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim"
    ): ShoppingItemsResponse
}