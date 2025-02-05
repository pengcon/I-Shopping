package com.example.ishopping.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ishopping.databinding.FragmentSearchBinding
import com.example.ishopping.ui.extensions.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewmodel>()
    private val pagingAdapter = SearchShoppingItemAdapter(SearchShoppingItemComparator)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeSearchTextChanges()
    }

    private fun setupRecyclerView() {
        binding.rvSearchedShoppingItemList.adapter = pagingAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.itemUiModel.collectLatest { itemUiModels ->
                pagingAdapter.submitData(itemUiModels)
            }
        }
    }

    private fun observeSearchTextChanges() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.searchHomeLayout.editTextSearchText.textChanges()
                    .collect { text ->
                        viewModel.updateSearchText(text)
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
