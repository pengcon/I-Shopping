package com.example.ishopping.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ishopping.R
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.ItemShoppingItemBinding
import com.example.ishopping.util.StringUtils

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

    fun addItems(shoppingItems: List<ShoppingItem>) {
        val startPosition = items.size
        items.addAll(shoppingItems)
        notifyItemRangeInserted(startPosition, shoppingItems.size)
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
                Log.d("title", shoppingItem.title.toString())
                tvShoppingItemTitle.text = StringUtils.stripHtml(shoppingItem.title)
                tvShoppingItemPrice.text = StringUtils.formatPrice(shoppingItem.lowPrice)
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