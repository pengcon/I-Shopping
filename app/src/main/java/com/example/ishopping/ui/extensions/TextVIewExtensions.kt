package com.example.ishopping.ui.extensions

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.util.Locale

@BindingAdapter("title")
fun TextView.stripHtml(input: String) {
    this.text = HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}

@BindingAdapter("price")
fun TextView.formatPrice(price: String) {
    val priceInt = price.toIntOrNull() ?: 0
    val formattedPrice = NumberFormat.getNumberInstance(Locale.KOREA).format(priceInt)
    this.text = String.format("%s 원", formattedPrice)
}