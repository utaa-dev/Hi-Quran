package com.example.hi_quran.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hi_quran.navigation.auth.AuthScreen
import com.example.hi_quran.navigation.main.MainNavigation
import com.example.hi_quran.feature.home.HomeScreen
import com.example.hi_quran.feature.auth.login.LoginScreen
import com.example.hi_quran.feature.auth.register.RegisterScreen
import com.example.hi_quran.feature.auth.welcome.WelcomeScreen
import com.example.hi_quran.feature.doa.TasbihDigitalScreen
import com.example.hi_quran.feature.home.HijriCalendarScreen
import com.example.hi_quran.feature.home.PrayerScheduleScreen
import com.example.hi_quran.feature.splash.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootScreen.Splash.route
    ) {
        composable(RootScreen.Splash.route) {
            SplashScreen(
                onDataLoaded = {
                    navController.navigate(AuthScreen.Welcome.route) {
                        popUpTo(RootScreen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AuthScreen.Welcome.route) {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate(AuthScreen.Login.route)
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
        composable(AuthScreen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(RootScreen.Main.route)
                },
                onRegisterClick = {
                    navController.navigate(AuthScreen.Register.route)
                }
            )
        }
        composable(AuthScreen.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    navController.navigate(AuthScreen.Login.route)
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
        composable(RootScreen.Main.route) {
            MainNavigation(
                onNavigateToTasbih = {
                    navController.navigate(RootScreen.TasbihDigital.route)
                },
                onNavigateToJadwalSholat = {
                    navController.navigate(RootScreen.JadwalSholat.route)
                },
                onNavigateToKalender = {
                    navController.navigate(RootScreen.KalenderHijriah.route)
                }
            )
        }
        composable(RootScreen.TasbihDigital.route) {
            TasbihDigitalScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(RootScreen.JadwalSholat.route) {
            PrayerScheduleScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(RootScreen.KalenderHijriah.route) {
            HijriCalendarScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}