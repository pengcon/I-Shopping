package com.example.ishopping.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ishopping.R
import com.example.ishopping.data.source.HomeRepository
import com.example.ishopping.data.source.remote.ShoppingService
import com.example.ishopping.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeRepository = HomeRepository(ShoppingService.create())

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
        setLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLayout() {
        val adapter = ShoppingItemAdapter()
        binding.rvShoppingItemList.adapter = adapter
        lifecycleScope.launch {
            val result = homeRepository.getShoppingItems(getString(R.string.label_bag))
            adapter.addItems(result)
        }
    }
}