package com.example.iqtest.presentation.auth.signup

data class SignupUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSignedUp: Boolean = false
)
