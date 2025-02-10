package com.example.ishopping.ui.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ishopping.databinding.ActivitySearchBinding
import com.example.ishopping.ui.extensions.textChanges
import com.example.ishopping.util.comparator.ShoppingItemComparator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel by viewModels<SearchViewmodel>()
    private val pagingAdapter =
        SearchShoppingItemAdapter(ShoppingItemComparator){
            viewModel.onBookmarkButtonClick(it)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeSearchTextChanges()
        setupRecyclerView()
    }

    private fun observeSearchTextChanges() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.searchHomeLayout.editTextSearchText.textChanges()
                    .collect { text ->
                        viewModel.updateSearchText(text)
                    }
            }
        }
    }
    private fun setupRecyclerView() {
        binding.rvSearchedShoppingItemList.adapter = pagingAdapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.shoppingItems.collectLatest { shoppingItems ->
                    pagingAdapter.submitData(shoppingItems)
                }
            }
        }
    }
}
