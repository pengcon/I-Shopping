package com.example.ishopping.ui.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("inverseVisibility")
fun View.setInverseVisibility(isLoading: Boolean) {
    visibility = if (isLoading) View.GONE else View.VISIBLE
}
