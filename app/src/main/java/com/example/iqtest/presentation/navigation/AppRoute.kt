package com.example.iqtest.presentation.navigation

sealed class AppRoute(val route: String) {
    data object Splash : AppRoute("splash")
    data object Onboarding : AppRoute("onboarding")
    data object Login : AppRoute("login")
    data object Signup : AppRoute("signup")
    data object Home : AppRoute("home")
    data object Leaderboard : AppRoute("leaderboard")
    data object Settings : AppRoute("settings")
    data object Test : AppRoute("test")
    data object Result : AppRoute("result")
}
