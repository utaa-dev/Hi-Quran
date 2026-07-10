package com.example.hi_quran.navigation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home: MainScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Quran: MainScreen(
        route = "quran",
        title = "Qur'an",
        icon = Icons.Default.MenuBook
    )
    data object Dzikir: MainScreen(
        route = "dzikir",
        title = "Tazkiyah",
        icon = Icons.Default.AutoAwesome
    )
    data object Hadith: MainScreen(
        route = "hadith",
        title = "Hadits",
        icon = Icons.Default.Article
    )
    data object Profile: MainScreen(
        route = "profile",
        title = "Profil",
        icon = Icons.Default.Person
    )
}
