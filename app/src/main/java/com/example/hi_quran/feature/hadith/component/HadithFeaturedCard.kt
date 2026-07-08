package com.example.hi_quran.feature.hadith.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HadithFeaturedCard(
    title: String = "Shahih Al-Bukhari",
    description: String = "Kumpulan hadits paling otentik yang diriwayatkan oleh Imam Bukhari.",
    onButtonClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "KOLEKSI UTAMA",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6CF092),
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                Text(
                    "Buka Kitab",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
