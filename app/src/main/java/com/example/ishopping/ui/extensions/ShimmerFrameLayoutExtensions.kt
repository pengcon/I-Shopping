package com.example.ishopping.ui.extensions

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("shimmerVisibility")
fun ShimmerFrameLayout.handleShimmerVisibility(isLoading: Boolean) {
    Log.d("handleShimmerVisibility", "isLoading: $isLoading")
    visibility = if (isLoading) View.VISIBLE else View.GONE
    Log.d("handleShimmerVisibility", "visibility: $visibility")
    if (isLoading) startShimmer() else stopShimmer()
}
