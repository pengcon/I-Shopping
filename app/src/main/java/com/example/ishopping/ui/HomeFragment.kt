package com.example.ishopping.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ishopping.data.model.ShoppingItemsResponse
import com.example.ishopping.databinding.FragmentHomeBinding
import com.example.ishopping.data.source.remote.ShoppingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        val shoppingService = ShoppingService.create()
        val result = shoppingService.getShoppingItems("신발")
        result.enqueue(object : Callback<ShoppingItemsResponse> {
            override fun onResponse(
                call: Call<ShoppingItemsResponse>,
                response: Response<ShoppingItemsResponse>
            ) {
                if (response.isSuccessful) {
                    val shoppingItemsResponse = response.body()
                    Log.d(
                        "HomeFragment",
                        "HomeFragment: ${shoppingItemsResponse}"
                    )
                } else {
                    Log.d("HomeFragment", "message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ShoppingItemsResponse>, t: Throwable) {
                Log.d("HomeFragment", "t: ${t.message}")
            }
        }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}