package com.example.iqtest.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqtest.domain.repository.AuthRepository
import com.example.iqtest.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        resolveStartDestination()
    }

    private fun resolveStartDestination() {
        viewModelScope.launch {
            val onboardingCompleted = userPreferencesRepository.onboardingCompleted.first()
            val isLoggedIn = authRepository.isLoggedIn()

            val destination = when {
                !onboardingCompleted -> "onboarding"
                !isLoggedIn -> "login"
                else -> "home"
            }

            _uiState.value = SplashUiState(
                isLoading = false,
                nextRoute = destination
            )
        }
    }
}
