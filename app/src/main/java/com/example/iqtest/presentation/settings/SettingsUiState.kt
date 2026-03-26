package com.example.iqtest.presentation.settings

data class SettingsUiState(
    val onboardingCompleted: Boolean = false,
    val userId: String? = null,
    val infoMessage: String? = null
)
