package com.example.iqtest.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqtest.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun completeOnboarding() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            userPreferencesRepository.setOnboardingCompleted(completed = true)
            _uiState.update { it.copy(isSaving = false, isCompleted = true) }
        }
    }
}
