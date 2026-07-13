package com.example.hi_quran.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _isImporting = MutableStateFlow(false)
    val isImporting = _isImporting.asStateFlow()

    init {
        // No longer needs to import data as it's pre-packaged in the asset database.
        _isImporting.value = false
    }
}
