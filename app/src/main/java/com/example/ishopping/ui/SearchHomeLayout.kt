package com.example.ishopping.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ishopping.databinding.ViewSearchHomeBinding

class SearchHomeLayout(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val binding: ViewSearchHomeBinding =
        ViewSearchHomeBinding.inflate(LayoutInflater.from(context), this, true)
}