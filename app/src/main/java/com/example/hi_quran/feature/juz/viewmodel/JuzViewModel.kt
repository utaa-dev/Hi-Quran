package com.example.hi_quran.feature.juz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.domain.model.Juz
import com.example.hi_quran.domain.usecase.juz.GetJuzUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class JuzViewModel @Inject constructor(
    private val getJuzUseCase: GetJuzUseCase
) : ViewModel() {

    private val _juzList = MutableStateFlow<List<Juz>>(emptyList())
    val juzList: StateFlow<List<Juz>> = _juzList.asStateFlow()

    init {
        loadJuz()
    }

    private fun loadJuz() {
        getJuzUseCase()
            .onEach { _juzList.value = it }
            .launchIn(viewModelScope)
    }
}
