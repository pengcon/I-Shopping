package com.example.ishopping.data.source.remote

import com.example.ishopping.BuildConfig
import com.example.ishopping.data.model.ShoppingItemsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ShoppingService {

    @GET("v1/search/shop.json")
    fun getShoppingItems(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim"
    ): Call<ShoppingItemsResponse>

    companion object {
        fun create(): ShoppingService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val originalRequest = chain.request()
                    val modifiedRequest = originalRequest.newBuilder()
                        .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                        .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                        .build()
                    chain.proceed(modifiedRequest)
                }
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShoppingService::class.java)
        }
    }
}