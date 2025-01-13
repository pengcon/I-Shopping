package com.example.ishopping.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ishopping.databinding.ViewBannerBinding

class BannerLayout(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val binding: ViewBannerBinding =
        ViewBannerBinding.inflate(LayoutInflater.from(context), this, true)
}