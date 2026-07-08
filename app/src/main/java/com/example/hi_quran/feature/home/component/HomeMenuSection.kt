package com.example.hi_quran.feature.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeMenuSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Menu Utama",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            HomeMenuItem(name = "Al-Qur'an", icon = Icons.AutoMirrored.Outlined.MenuBook, color = Color(0xFFE7F3EF))
            HomeMenuItem(name = "Hadits", icon = Icons.AutoMirrored.Outlined.LibraryBooks, color = Color(0xFFFFF4E5))
            HomeMenuItem(name = "Dzikir", icon = Icons.Outlined.AutoAwesome, color = Color(0xFFEBF2FF))
            HomeMenuItem(name = "Profil", icon = Icons.Outlined.PersonOutline, color = Color(0xFFF3E8FF))
        }
    }
}

@Composable
fun HomeMenuItem(name: String, icon: ImageVector, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(16.dp),
            color = color
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(28.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
    }
}
