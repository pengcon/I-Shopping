package com.example.ishopping.ui.setting

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ishopping.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by viewModels()

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
        setUpClickListeners()
        observeMode()
    }

    private fun setUpClickListeners() {
        binding.layoutDarkMode.setOnClickListener {
            viewModel.onModeClick(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.layoutWhiteMode.setOnClickListener {
            viewModel.onModeClick(AppCompatDelegate.MODE_NIGHT_NO)
        }
        binding.layoutSystemMode.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                viewModel.onModeClick(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            } else {
                viewModel.onModeClick(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            }
        }
    }

    private fun observeMode() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mode.collect { mode ->

                    when (mode) {
                        AppCompatDelegate.MODE_NIGHT_YES -> {
                            binding.switchDarkMode.isChecked = true
                            binding.switchWhiteMode.isChecked = false
                            binding.switchSystemMode.isChecked = false
                        }

                        AppCompatDelegate.MODE_NIGHT_NO -> {
                            binding.switchDarkMode.isChecked = false
                            binding.switchWhiteMode.isChecked = true
                            binding.switchSystemMode.isChecked = false
                        }

                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                            binding.switchDarkMode.isChecked = false
                            binding.switchWhiteMode.isChecked = false
                            binding.switchSystemMode.isChecked = true
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
