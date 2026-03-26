package com.example.iqtest.presentation.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqtest.domain.repository.TestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    private val testRepository: TestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LeaderboardUiState())
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()

    init {
        loadLeaderboard()
    }

    fun loadLeaderboard() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching { testRepository.getLeaderboard() }
                .onSuccess { users ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            users = users
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Could not load leaderboard."
                        )
                    }
                }
        }
    }
}
