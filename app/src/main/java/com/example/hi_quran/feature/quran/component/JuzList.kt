package com.example.hi_quran.feature.quran.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hi_quran.domain.model.Juz

@Composable
fun JuzList(
    juzList: List<Juz>,
    onJuzClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(juzList, key = { it.number }) { juz ->
            JuzCard(juz = juz, onClick = { onJuzClick(juz.number) })
        }
    }
}
