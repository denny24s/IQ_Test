package com.example.iqtest.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val onboardingCompleted: Flow<Boolean>

    suspend fun setOnboardingCompleted(completed: Boolean)
}
