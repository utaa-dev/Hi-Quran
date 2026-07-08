package com.example.hi_quran.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.domain.usecase.quran.ImportQuranUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val importQuranUseCase: ImportQuranUseCase
) : ViewModel() {

    private val _isImporting = MutableStateFlow(true)
    val isImporting = _isImporting.asStateFlow()

    init {
        importData()
    }

    private fun importData() {
        viewModelScope.launch {
            _isImporting.value = true
            importQuranUseCase()
            _isImporting.value = false
        }
    }
}
