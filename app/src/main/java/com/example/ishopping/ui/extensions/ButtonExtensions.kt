package com.example.ishopping.ui.extensions

import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.BindingAdapter

@BindingAdapter("isBookmarked")
fun AppCompatImageButton.setBookmarkState(isBookmarked: Boolean) {
    isSelected = isBookmarked
}

@BindingAdapter("onBookmarkClick")
fun AppCompatImageButton.setOnBookmarkClickListener(onBookmarkClick: () -> Unit) {
    setOnClickListener {
        onBookmarkClick()
    }
}