package com.example.hi_quran.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrayerCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
    ) {
        Column(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SHOLAT BERIKUTNYA",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Surface(shape = CircleShape, color = Color.White.copy(alpha = 0.15f)) {
                    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.Schedule, null, tint = Color.White, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "-01:11:42", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Maghrib", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Box(modifier = Modifier.size(6.dp).background(Color.White, CircleShape))
            }
            Text(text = "17:49", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                PrayerTimeSmall(name = "Subuh", time = "04:35")
                PrayerTimeSmall(name = "Dzuhur", time = "11:58")
                PrayerTimeSmall(name = "Ashar", time = "15:20")
                Surface(shape = RoundedCornerShape(20.dp), color = Color.White, modifier = Modifier.width(75.dp).offset(y = 8.dp)) {
                    Column(modifier = Modifier.padding(vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Maghrib", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "17:49", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
                PrayerTimeSmall(name = "Isya", time = "18:59")
            }
        }
    }
}

@Composable
fun PrayerTimeSmall(name: String, time: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = name, color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
        Text(text = time, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}
