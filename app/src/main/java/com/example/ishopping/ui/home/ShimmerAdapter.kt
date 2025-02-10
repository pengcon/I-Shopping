package com.example.ishopping.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.databinding.ItemShimmerShoppingItemBinding

class ShimmerAdapter : RecyclerView.Adapter<ShimmerAdapter.ShimmerViewHolder>() {
    private val itemCount = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        return ShimmerViewHolder(
            ItemShimmerShoppingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = itemCount

    class ShimmerViewHolder(
        val binding: ItemShimmerShoppingItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
