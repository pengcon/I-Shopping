package com.example.ishopping.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ishopping.data.model.ItemUiModel
import com.example.ishopping.databinding.ItemSearchShoppingItemBinding
import com.example.ishopping.ui.search.SearchShoppingItemAdapter.SearchShoppingItemViewHolder

class SearchShoppingItemAdapter(diffCallback: DiffUtil.ItemCallback<ItemUiModel>) :
    PagingDataAdapter<ItemUiModel, SearchShoppingItemViewHolder>(diffCallback) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchShoppingItemViewHolder {
        return SearchShoppingItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: SearchShoppingItemViewHolder,
        position: Int
    ) {
        val itemUiModel = getItem(position)
        itemUiModel?.let { item ->
            holder.bind(item)
        } ?: run {
            holder.bind(ItemUiModel.placeholder())
        }
    }

    class SearchShoppingItemViewHolder(private val binding: ItemSearchShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUiModel: ItemUiModel) {
            binding.itemUiModel = itemUiModel
        }

        companion object {
            fun from(parent: ViewGroup): SearchShoppingItemViewHolder {
                val binding = ItemSearchShoppingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return SearchShoppingItemViewHolder(binding)
            }
        }
    }
}
