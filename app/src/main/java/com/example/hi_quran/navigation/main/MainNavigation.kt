package com.example.hi_quran.navigation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hi_quran.feature.doa.DzikirScreen
import com.example.hi_quran.feature.doa.screen.DoaListScreen
import com.example.hi_quran.feature.hadith.HadithScreen
import com.example.hi_quran.feature.home.HomeScreen
import com.example.hi_quran.feature.profile.ProfileScreen
import com.example.hi_quran.feature.quran.screen.JuzDetailScreen
import com.example.hi_quran.feature.quran.screen.QuranHomeScreen
import com.example.hi_quran.feature.quran.screen.SurahDetailScreen

@Composable
fun MainNavigation(
    onNavigateToTasbih: () -> Unit,
    onNavigateToJadwalSholat: () -> Unit,
    onNavigateToKalender: () -> Unit
) {
    val navController = rememberNavController()

    val bottomItems = listOf(
        MainScreen.Home,
        MainScreen.Quran,
        MainScreen.Dzikir,
        MainScreen.Hadith,
        MainScreen.Profile,
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = bottomItems
            )
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = MainScreen.Home.route,
            modifier = Modifier.padding(bottom = padding.calculateBottomPadding())
        ) {
            composable(MainScreen.Home.route) {
                HomeScreen(
                    onSurahClick = { surahNumber ->
                        navController.navigate("surah_detail/$surahNumber")
                    },
                    onJadwalSholatClick = onNavigateToJadwalSholat,
                    onKalenderHijriahClick = onNavigateToKalender
                )
            }
            composable(MainScreen.Quran.route) {
                QuranHomeScreen(
                    onSurahClick = { surahNumber ->
                        navController.navigate("surah_detail/$surahNumber")
                    },
                    onJuzClick = { juzNumber ->
                        navController.navigate("juz_detail/$juzNumber")
                    }
                )
            }
            composable(
                route = "surah_detail/{surahNumber}",
                arguments = listOf(navArgument("surahNumber") { type = NavType.IntType })
            ) { backStackEntry ->
                val surahNumber = backStackEntry.arguments?.getInt("surahNumber") ?: 1
                SurahDetailScreen(
                    surahNumber = surahNumber,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = "juz_detail/{juzNumber}",
                arguments = listOf(navArgument("juzNumber") { type = NavType.IntType })
            ) { backStackEntry ->
                val juzNumber = backStackEntry.arguments?.getInt("juzNumber") ?: 1
                JuzDetailScreen(
                    juzNumber = juzNumber,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
            composable("hafalan_detail") {
                // Placeholder
            }
            composable(MainScreen.Dzikir.route) {
                DzikirScreen(
                    onTasbihClick = { onNavigateToTasbih() },
                    onSeeAllDoaClick = { navController.navigate("doa_list") }
                )
            }
            composable("doa_list") {
                DoaListScreen(
                    onBackClick = { navController.popBackStack() },
                    onDoaClick = { doa ->
                        // Navigate to detail if available
                    }
                )
            }
            composable(MainScreen.Hadith.route) {
                HadithScreen()
            }
            composable(MainScreen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
