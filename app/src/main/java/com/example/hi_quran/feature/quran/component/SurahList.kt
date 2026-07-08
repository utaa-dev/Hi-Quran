package com.example.hi_quran.feature.quran.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
        itemsIndexed(surahs, key = { _, surah -> surah.number }) { index, surah ->
            SurahCard(surah = surah, onClick = { onSurahClick(surah.number) })
            
            // Insert Murottal Audio Banner after the 5th item (index 4)
            if (index == 4) {
                MurottalBanner(
                    modifier = Modifier.padding(vertical = 4.dp),
                    onPilihQariClick = { /* TODO: Implement Qari Selection */ }
                )
            }
        }
    }
}
