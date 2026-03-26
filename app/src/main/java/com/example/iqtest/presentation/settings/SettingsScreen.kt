package com.example.iqtest.presentation.settings

import androidx.compose.runtime.Composable
import com.example.iqtest.presentation.common.PlaceholderScreenScaffold

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onLogout: () -> Unit,
    onResetOnboarding: () -> Unit,
    onBack: () -> Unit
) {
    PlaceholderScreenScaffold(
        title = "Settings",
        description = buildString {
            appendLine("Onboarding completed: ${uiState.onboardingCompleted}")
            appendLine("User ID: ${uiState.userId ?: "Not logged in"}")
            appendLine()
            appendLine("TODO: Add language/theme/account settings UI.")
        },
        primaryActionText = "Logout",
        onPrimaryAction = onLogout,
        secondaryActionText = "Reset Onboarding",
        onSecondaryAction = onResetOnboarding,
        tertiaryActionText = "Back",
        onTertiaryAction = onBack
    )
}
