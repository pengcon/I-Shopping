package com.example.ishopping.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ishopping.R
import com.example.ishopping.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeShoppingItemViewmodel>()
    private val adapter = HomeShoppingItemAdapter()
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
        binding.rvShoppingItemList.adapter = adapter
        viewModel.loadShoppingItems(getString(R.string.label_bag))
        viewModel.items.observe(viewLifecycleOwner) { shoppingItems ->
            adapter.addItems(shoppingItems)
        }
        binding.searchHomeLayout.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_searchFragment)
        }
    }
}
