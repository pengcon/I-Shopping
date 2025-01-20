package com.example.ishopping.util

import androidx.core.text.HtmlCompat
import java.text.NumberFormat
import java.util.Locale

object StringUtils {

    fun stripHtml(input: String): String {
        return HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }

    fun formatPrice(price: String): String {
        val priceInt = price.toIntOrNull() ?: 0
        val formattedPrice = NumberFormat.getNumberInstance(Locale.KOREA).format(priceInt)
        return String.format("%s 원", formattedPrice)
    }
}