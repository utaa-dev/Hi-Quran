package com.example.hi_quran.feature.doa.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionHeader(
    title: String,
    showAction: Boolean = true,
    onActionClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        if (showAction) {
            Text(
                text = "Lihat Semua",
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { onActionClick() }
            )
        }
    }
}
