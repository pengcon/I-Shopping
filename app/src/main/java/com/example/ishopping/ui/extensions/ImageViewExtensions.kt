package com.example.ishopping.ui.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ishopping.R

@BindingAdapter("imageUrl")
fun ImageView.load(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this)
            .load(url)
            .placeholder(R.color.greyscale_600)
            .error(R.drawable.ic_image_not_supported)
            .into(this)
    } else {
        setImageResource(R.drawable.ic_image_not_supported)
    }
}