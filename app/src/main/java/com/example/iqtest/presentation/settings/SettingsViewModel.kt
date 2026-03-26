package com.example.iqtest.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqtest.domain.repository.AuthRepository
import com.example.iqtest.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SettingsUiState(
            userId = authRepository.currentUserId()
        )
    )
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        observeOnboardingFlag()
    }

    private fun observeOnboardingFlag() {
        viewModelScope.launch {
            userPreferencesRepository.onboardingCompleted.collect { completed ->
                _uiState.update { it.copy(onboardingCompleted = completed) }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            _uiState.update {
                it.copy(
                    userId = null,
                    infoMessage = "Logged out."
                )
            }
        }
    }

    fun resetOnboarding() {
        viewModelScope.launch {
            userPreferencesRepository.setOnboardingCompleted(false)
            _uiState.update { it.copy(infoMessage = "Onboarding reset.") }
        }
    }
}
