package com.example.iqtest.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.iqtest.presentation.common.PlaceholderScreenScaffold

@Composable
fun SplashScreen(
    uiState: SplashUiState,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    LaunchedEffect(uiState.isLoading, uiState.nextRoute) {
        if (uiState.isLoading) return@LaunchedEffect
        when (uiState.nextRoute) {
            "onboarding" -> onNavigateToOnboarding()
            "login" -> onNavigateToLogin()
            "home" -> onNavigateToHome()
        }
    }

    PlaceholderScreenScaffold(
        title = "Splash",
        description = "Preparing app state and choosing first destination."
    )
}
