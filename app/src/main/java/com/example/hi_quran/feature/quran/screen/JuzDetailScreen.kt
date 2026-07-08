package com.example.hi_quran.feature.quran.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hi_quran.domain.model.Juz
import com.example.hi_quran.feature.quran.viewmodel.JuzDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuzDetailScreen(
    juzNumber: Int,
    onBackClick: () -> Unit,
    viewModel: JuzDetailViewModel = hiltViewModel()
) {
    val juz by viewModel.juz.collectAsState()
    val ayahs by viewModel.ayahs.collectAsState()
    val surahNames by viewModel.surahNames.collectAsState()

    LaunchedEffect(juzNumber) {
        viewModel.loadJuzDetail(juzNumber)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = juz?.let { "Juz ${it.number}" } ?: "Loading...",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                JuzHeader(juz)
            }

            items(ayahs, key = { "${it.surahNumber}_${it.number}" }) { ayah ->
                val surahName = surahNames[ayah.surahNumber] ?: ""
                AyahItem(
                    ayah = ayah,
                    surahName = surahName,
                    onBookmarkClick = { viewModel.addBookmark(ayah, surahName) },
                    onLastReadClick = { viewModel.updateLastRead(ayah, surahName) }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun JuzHeader(juz: Juz?) {
    juz?.let {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Juz ${it.number}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }
        }
    }
}
