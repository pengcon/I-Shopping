package com.example.ishopping.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.ishopping.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutDarkMode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.layoutWhiteMode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        binding.layoutSystemMode.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
