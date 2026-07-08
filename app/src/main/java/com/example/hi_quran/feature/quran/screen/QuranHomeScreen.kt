package com.example.hi_quran.feature.quran.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hi_quran.feature.quran.component.JuzList
import com.example.hi_quran.feature.quran.component.SearchBar
import com.example.hi_quran.feature.quran.component.SurahList
import com.example.hi_quran.feature.quran.viewmodel.QuranViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranHomeScreen(
    onSurahClick: (Int) -> Unit,
    onJuzClick: (Int) -> Unit,
    viewModel: QuranViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Surah", "Juz")
    val surahs by viewModel.surahs.collectAsState()
    val juzList by viewModel.juzList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
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
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                SearchBar(
                    value = searchQuery,
                    onValueChange = viewModel::onSearchQueryChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Cari Surah..."
                )
            }

            PrimaryTabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            AnimatedContent(
                targetState = selectedTab,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "QuranTabAnimation"
            ) { targetTab ->
                when (targetTab) {
                    0 -> SurahList(surahs, onSurahClick)
                    1 -> JuzList(juzList, onJuzClick)
                }
            }
        }
    }
}
