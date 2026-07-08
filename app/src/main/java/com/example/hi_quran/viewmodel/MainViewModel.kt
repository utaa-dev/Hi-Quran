package com.example.hi_quran.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.data.local.DataStoreManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStoreManager = DataStoreManager(application)
    
    val isDarkMode = dataStoreManager.isDarkMode.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setDarkMode(enabled)
        }
    }
}
