package com.example.ishopping.ui.extensions

import android.view.View
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("shimmerVisibility")
fun ShimmerFrameLayout.handleShimmerVisibility(isLoading: Boolean) {
    visibility = if (isLoading) View.VISIBLE else View.GONE
    if (isLoading) startShimmer() else stopShimmer()
}
