package com.example.hi_quran.feature.doa

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Pin
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hi_quran.feature.doa.component.*

@Composable
fun DzikirScreen(
    onTasbihClick: () -> Unit = {},
    onSeeAllDoaClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // 1. Dzikir Pagi & Petang Card
            DzikirHeaderCard()

            Spacer(modifier = Modifier.height(20.dp))

            // 2. Quick Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    title = "Tasbih Digital",
                    icon = Icons.Outlined.Pin,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    onClick = onTasbihClick
                )
                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    title = "Riwayat",
                    icon = Icons.Default.History,
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Setelah Sholat Section
            SectionHeader(title = "Setelah Sholat", onActionClick = {})
            Spacer(modifier = Modifier.height(12.dp))
            DzikirListItem(number = "01", title = "Istighfar & Tahlil", subtitle = "3x Bacaan Utama")
            Spacer(modifier = Modifier.height(12.dp))
            DzikirListItem(number = "02", title = "Tasbih, Tahmid, Takbir", subtitle = "33x Repetisi")

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Koleksi Doa Section
            SectionHeader(title = "Koleksi Doa", onActionClick = onSeeAllDoaClick)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DoaCard(
                    modifier = Modifier.weight(1f),
                    title = "Doa Kedua Orang Tua",
                    subtitle = "Berbakti & Kasih Sayang",
                    icon = Icons.Outlined.People,
                    onClick = onSeeAllDoaClick
                )
                DoaCard(
                    modifier = Modifier.weight(1f),
                    title = "Doa Sapu Jagad",
                    subtitle = "Kebaikan Dunia Akhirat",
                    icon = Icons.Outlined.Public,
                    onClick = onSeeAllDoaClick
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Koleksi Sholawat Section
            SectionHeader(title = "Koleksi Sholawat", showAction = false)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SholawatCard(
                    modifier = Modifier.weight(1f),
                    title = "Sholawat Jibril",
                    subtitle = "Penarik Rezeki",
                    icon = Icons.Outlined.AutoAwesome,
                    isFavorite = true
                )
                SholawatCard(
                    modifier = Modifier.weight(1f),
                    title = "Sholawat Nariyah",
                    subtitle = "Pelancar Hajat",
                    icon = Icons.Outlined.FavoriteBorder,
                    isFavorite = false
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Quote Card
            QuoteCard()
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
