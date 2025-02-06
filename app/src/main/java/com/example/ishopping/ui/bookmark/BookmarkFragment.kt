package com.example.ishopping.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ishopping.data.model.ShoppingItem
import com.example.ishopping.databinding.FragmentBookmarkBinding
import com.example.ishopping.util.BookmarkClickListener
import com.example.ishopping.util.comparator.ShoppingItemComparator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment(), BookmarkClickListener {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<BookmarkViewmodel>()
    private val adapter =
        BookmarkShoppingItemAdapter(ShoppingItemComparator, this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBookmarkButtonClick(shoppingItem: ShoppingItem) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
