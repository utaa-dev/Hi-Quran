package com.example.hi_quran.navigation

sealed class RootScreen(
    val route: String
){
    data object Splash : RootScreen("splash")

    data object Auth : RootScreen("auth")

    data object Main : RootScreen("main")

    data object TasbihDigital : RootScreen("tasbih_digital")
}
