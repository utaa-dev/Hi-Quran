package com.example.hi_quran.feature.quran.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hi_quran.domain.model.Surah

@Composable
fun SurahList(
    surahs: List<Surah>,
    onSurahClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Ambil 5 surah pertama
        val firstFive = surahs.take(5)
        items(
            items = firstFive,
            key = { it.number }
        ) { surah ->
            SurahCard(surah = surah, onClick = { onSurahClick(surah.number) })
        }

        // Sisipkan Murottal Audio Banner setelah item ke-5
        if (surahs.size >= 5) {
            item(key = "murottal_banner") {
                MurottalBanner(
                    modifier = Modifier.padding(vertical = 4.dp),
                    onPilihQariClick = { /* TODO: Implement Qari Selection */ }
                )
            }
        }

        // Tampilkan sisa surah lainnya
        if (surahs.size > 5) {
            val remainingSurahs = surahs.drop(5)
            items(
                items = remainingSurahs,
                key = { it.number }
            ) { surah ->
                SurahCard(surah = surah, onClick = { onSurahClick(surah.number) })
            }
        }
    }
}
