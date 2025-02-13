package com.example.ishopping.ui.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ishopping.data.source.repoistory.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val settingRepository: SettingRepository) :
    ViewModel() {

    private val _mode = MutableStateFlow<Int>(-100) // MODE_NIGHT_UNSPECIFIED
    val mode: StateFlow<Int> = _mode.asStateFlow()

    init {
        viewModelScope.launch {
            _mode.value = settingRepository.flowDarkModeIds().first()
        }
    }

    fun onModeClick(mode: Int) {
        viewModelScope.launch {
            Log.d("SettingViewModel", "onModeClick: $mode")
            settingRepository.updateDarkModeId(mode)
        }
        _mode.value = mode
    }
}
