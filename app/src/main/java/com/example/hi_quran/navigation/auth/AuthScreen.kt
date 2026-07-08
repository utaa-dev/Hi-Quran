package com.example.hi_quran.navigation.auth

sealed class AuthScreen(val route: String) {

    data object Welcome : AuthScreen("Welcome")
    data object Login : AuthScreen("login")
    data object Register : AuthScreen("register")
    data object Home : AuthScreen("home")
}