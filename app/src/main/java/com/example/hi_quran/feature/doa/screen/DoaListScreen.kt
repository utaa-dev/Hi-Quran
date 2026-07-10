package com.example.hi_quran.feature.doa.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hi_quran.domain.model.Doa
import com.example.hi_quran.feature.doa.component.DoaDailyBanner
import com.example.hi_quran.feature.doa.component.DoaListItem
import com.example.hi_quran.feature.doa.viewmodel.DoaViewModel
import com.example.hi_quran.feature.quran.component.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoaListScreen(
    onBackClick: () -> Unit,
    onDoaClick: (Doa) -> Unit,
    viewModel: DoaViewModel = hiltViewModel()
) {
    val doas by viewModel.doas.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    val categories = listOf("Semua", "Harian", "Sholat", "Quran & Hadits")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Semua Doa",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SearchBar(
                    value = searchQuery,
                    onValueChange = viewModel::onSearchQueryChange,
                    placeholder = "Cari doa pilihan Anda..."
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { viewModel.onCategoryChange(category) },
                            label = { Text(category) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                borderColor = Color.Transparent,
                                enabled = true,
                                selected = selectedCategory == category
                            ),
                            shape = RoundedCornerShape(50.dp)
                        )
                    }
                }
            }

            item {
                DoaDailyBanner()
            }

            item {
                Text(
                    text = "Kumpulan Doa",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(doas) { doa ->
                DoaListItem(doa = doa, onClick = { onDoaClick(doa) })
            }
        }
    }
}
