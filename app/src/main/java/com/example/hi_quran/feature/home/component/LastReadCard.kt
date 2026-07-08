package com.example.hi_quran.feature.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LastReadCard(surahName: String = "Al-Baqarah: 286", progress: Float = 0.75f, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(64.dp)) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 6.dp,
                    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                )
                Text(text = "${(progress * 100).toInt()}%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Terakhir Dibaca", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = surahName, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(text = "Ayat Terakhir", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Surface(modifier = Modifier.size(48.dp), shape = CircleShape, color = MaterialTheme.colorScheme.primary) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Filled.PlayArrow, null, tint = Color.White, modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}
