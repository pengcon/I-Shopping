package com.example.ishopping.data.model

import com.google.gson.annotations.SerializedName

data class ShoppingItemsResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<ShoppingItem>
)

data class ShoppingItem(
    val title: String,
    val link: String,
    val image: String,
    @SerializedName("lprice") val lowPrice: String,
    @SerializedName("hprice") val highPrice: String?,
    val mallName: String,
    val productId: String,
    val productType: String,
    val brand: String?,
    val maker: String?,
    val category1: String,
    val category2: String,
    val category3: String,
    val category4: String?
) {
    companion object{
        fun placeholder() = ShoppingItem(
            title = "",
            link = "",
            image = "",
            lowPrice = "",
            highPrice = null,
            mallName = "",
            productId = "",
            productType = "",
            brand = null,
            maker = null,
            category1 = "",
            category2 = "",
            category3 = "",
            category4 = null
        )
    }
}
