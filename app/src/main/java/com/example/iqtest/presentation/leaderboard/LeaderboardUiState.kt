package com.example.iqtest.presentation.leaderboard

import com.example.iqtest.domain.model.LeaderboardUser

data class LeaderboardUiState(
    val users: List<LeaderboardUser> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
