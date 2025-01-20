package com.example.ishopping.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ishopping.R
import com.example.ishopping.data.source.HomeRepository
import com.example.ishopping.databinding.FragmentHomeBinding
import com.example.ishopping.ui.home.ShoppingItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var  homeRepository : HomeRepository

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