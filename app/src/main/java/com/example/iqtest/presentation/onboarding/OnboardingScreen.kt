package com.example.iqtest.presentation.onboarding

import androidx.compose.runtime.Composable
import com.example.iqtest.presentation.common.PlaceholderScreenScaffold

@Composable
fun OnboardingScreen(
    uiState: OnboardingUiState,
    onComplete: () -> Unit
) {
    PlaceholderScreenScaffold(
        title = "Onboarding",
        description = buildString {
            appendLine("Quick onboarding flow placeholder.")
            appendLine("Completed: ${uiState.isCompleted}")
            appendLine()
            appendLine("TODO: Replace this with your final onboarding visuals/screenshots.")
        },
        primaryActionText = if (uiState.isSaving) "Saving..." else "Complete Onboarding",
        onPrimaryAction = if (uiState.isSaving) null else onComplete
    )
}
