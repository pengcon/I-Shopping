package com.example.ishopping.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.ishopping.R
import com.example.ishopping.databinding.FragmentHomeBinding
import com.example.ishopping.util.comparator.ShoppingItemComparator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeShoppingItemViewmodel>()
    private val adapter = HomeShoppingItemAdapter(ShoppingItemComparator) { shoppingItem ->
        viewModel.onBookmarkButtonClick(shoppingItem)
    }
    private val shimmerAdapter = ShimmerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLayout() {
        binding.rvShimmerList.adapter = shimmerAdapter
        binding.rvShoppingItemList.adapter = adapter
        viewModel.loadShoppingItems(getString(R.string.label_bag))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    binding.isLoading = isLoading
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { shoppingItems ->
                    adapter.submitList(shoppingItems)
                }
            }
        }

        binding.searchHomeLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToSearchActivity()
            findNavController().navigate(action)
        }
    }
}
