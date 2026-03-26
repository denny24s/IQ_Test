package com.example.iqtest.data.repository

import com.example.iqtest.data.local.UserPreferencesDataSource
import com.example.iqtest.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepositoryImpl(
    private val dataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override val onboardingCompleted: Flow<Boolean> = dataSource.onboardingCompleted

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        dataSource.setOnboardingCompleted(completed)
    }
}
