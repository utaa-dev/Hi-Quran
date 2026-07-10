package com.example.hi_quran.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hi_quran.feature.home.component.*
import com.example.hi_quran.feature.home.viewmodel.HomeViewModel
import com.example.hi_quran.feature.quran.component.SurahCard

@Composable
fun HomeScreen(
    onSurahClick: (Int) -> Unit,
    onJadwalSholatClick: () -> Unit,
    onKalenderHijriahClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val surahs by viewModel.surahs.collectAsState()
    val lastRead by viewModel.lastRead.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                HomeHeader(userName = "Utaa")
            }
            
            item {
                PrayerCard()
            }

            item {
                HomeQuickActions(
                    onJadwalSholatClick = onJadwalSholatClick,
                    onKalenderHijriahClick = onKalenderHijriahClick
                )
            }

            item {
                LastReadCard(
                    surahName = lastRead?.surahName ?: "Al-Baqarah: 286",
                    onClick = { 
                        lastRead?.let { onSurahClick(it.surahNumber) }
                    }
                )
            }

            item {
                HomeMenuSection()
            }

            item {
                DailyAyahCard()
            }

            item {
                Text(
                    text = "Surah Populer",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            items(surahs.take(5)) { surah ->
                SurahCard(surah = surah, onClick = { onSurahClick(surah.number) })
            }
        }
    }
}
