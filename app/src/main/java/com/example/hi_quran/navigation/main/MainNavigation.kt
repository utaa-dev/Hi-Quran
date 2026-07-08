package com.example.hi_quran.navigation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hi_quran.feature.doa.DzikirScreen
import com.example.hi_quran.feature.hadith.HadithScreen
import com.example.hi_quran.feature.home.HomeScreen
import com.example.hi_quran.feature.profile.ProfileScreen
import com.example.hi_quran.feature.quran.screen.JuzDetailScreen
import com.example.hi_quran.feature.quran.screen.QuranHomeScreen
import com.example.hi_quran.feature.quran.screen.SurahDetailScreen

@Composable
fun MainNavigation(
    onNavigateToTasbih: () -> Unit
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
        containerColor = Color.White,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = bottomItems
            )
        },
        // Penting: Scaffold luar ini menangani insets bawah (bottom bar)
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = MainScreen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(MainScreen.Home.route) {
                HomeScreen(
                    onSurahClick = { surahNumber ->
                        navController.navigate("surah_detail/$surahNumber")
                    }
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
                DzikirScreen {
                    onNavigateToTasbih()
                }
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
