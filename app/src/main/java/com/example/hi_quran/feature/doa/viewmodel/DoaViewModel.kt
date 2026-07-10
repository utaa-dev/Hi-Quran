package com.example.hi_quran.feature.doa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hi_quran.domain.model.Doa
import com.example.hi_quran.domain.usecase.doa.GetDoasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DoaViewModel @Inject constructor(
    private val getDoasUseCase: GetDoasUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("Semua")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    val doas: StateFlow<List<Doa>> = combine(
        getDoasUseCase(),
        _searchQuery,
        _selectedCategory
    ) { doas, query, category ->
        doas.filter { doa ->
            (query.isEmpty() || doa.title.contains(query, ignoreCase = true)) &&
            (category == "Semua" || true) // Category filtering placeholder as data doesn't have category yet
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onCategoryChange(category: String) {
        _selectedCategory.value = category
    }
}
