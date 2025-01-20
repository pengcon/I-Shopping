package com.example.ishopping.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ishopping.R
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.ItemShoppingItemBinding
import kotlin.math.PI

class ShoppingItemAdapter : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    private val items = mutableListOf<ShoppingItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingItemViewHolder {
        return ShoppingItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: ShoppingItemViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    class ShoppingItemViewHolder(private val binding: ItemShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shoppingItem: ShoppingItem) {
            with(binding) {
                if (shoppingItem.image.isNotEmpty()) {
                    Glide.with(ivShoppingItemImage)
                        .load(shoppingItem.image)
                        .placeholder(R.color.greyscale_600)
                        .error(R.drawable.ic_image_not_supported)
                        .into(ivShoppingItemImage)
                } else {
                    ivShoppingItemImage.setImageResource(R.drawable.ic_image_not_supported)
                }
                binding.tvShoppingItemTitle.text = shoppingItem.title
                binding.tvShoppingItemPrice.text = shoppingItem.lowPrice.toString()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ShoppingItemViewHolder {
                val binding = ItemShoppingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ShoppingItemViewHolder(binding)
            }
        }
    }
}