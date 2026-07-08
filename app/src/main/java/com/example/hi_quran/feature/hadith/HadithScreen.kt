package com.example.hi_quran.feature.hadith

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hi_quran.feature.hadith.component.HadithFeaturedCard
import com.example.hi_quran.feature.quran.component.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HadithScreen() {
    var search by remember { mutableStateOf("") }
    var selectedKitab by remember { mutableStateOf("Bukhari") }
    val kitabList = listOf("Bukhari", "Muslim", "Riyadhus Shalihin", "Abu Dawud")

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Hi-Qur'an",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary

                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 20.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Search Bar
            item {
                SearchBar(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = "Cari Hadits..."
                )
            }

            // Kitab Populer Section
            item {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Kitab Populer",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        TextButton(onClick = { }) {
                            Text(
                                "LIHAT SEMUA",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(kitabList) { kitab ->
                            FilterChip(
                                selected = selectedKitab == kitab,
                                onClick = { selectedKitab = kitab },
                                label = { Text(kitab) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                border = null,
                                shape = RoundedCornerShape(20.dp)
                            )
                        }
                    }
                }
            }

            // Featured Card (Koleksi Utama)
            item {
                HadithFeaturedCard(
                    onButtonClick = { /* Open Kitab */ }
                )
            }

            // Hadits Harian Section
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hadits Harian",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Placeholder for Hadith Items
            item {
                Text(
                    text = "Hadits items will appear here...",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
