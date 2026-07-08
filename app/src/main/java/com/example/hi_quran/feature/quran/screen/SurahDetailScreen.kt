package com.example.hi_quran.feature.quran.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hi_quran.domain.model.Ayah
import com.example.hi_quran.domain.model.Surah
import com.example.hi_quran.feature.quran.viewmodel.SurahDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahDetailScreen(
    surahNumber: Int,
    onBackClick: () -> Unit,
    viewModel: SurahDetailViewModel = hiltViewModel()
) {
    val surah by viewModel.surah.collectAsState()
    val ayahs by viewModel.ayahs.collectAsState()

    LaunchedEffect(surahNumber) {
        viewModel.loadSurahDetail(surahNumber)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = surah?.englishName ?: "Loading...",
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
                SurahHeader(surah)
            }

            items(ayahs, key = { it.number }) { ayah ->
                AyahItem(
                    ayah = ayah,
                    surahName = surah?.englishName ?: "",
                    onBookmarkClick = { viewModel.addBookmark(ayah, surah?.englishName ?: "") },
                    onLastReadClick = { viewModel.updateLastRead(ayah, surah?.englishName ?: "") }
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
fun SurahHeader(surah: Surah?) {
    surah?.let {
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
                    text = it.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = it.englishNameTranslation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = it.revelationType,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = " • ", style = MaterialTheme.typography.labelLarge)
                    Text(
                        text = "${it.numberOfAyahs} Ayahs",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun AyahItem(
    ayah: Ayah,
    surahName: String,
    onBookmarkClick: () -> Unit,
    onLastReadClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = ayah.number.toString(),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Row {
                IconButton(onClick = onBookmarkClick) {
                    Icon(
                        Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onLastReadClick) {
                    Icon(
                        Icons.Default.History,
                        contentDescription = "Last Read",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = ayah.text,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.End,
            lineHeight = 44.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = ayah.transliteration,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = ayah.translation,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Justify
        )
    }
}
