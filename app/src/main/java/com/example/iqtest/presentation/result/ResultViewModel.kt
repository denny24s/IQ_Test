package com.example.iqtest.presentation.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqtest.domain.repository.TestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ResultViewModel(
    private val testRepository: TestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    init {
        observeLatestResult()
    }

    private fun observeLatestResult() {
        testRepository.observeResultHistory()
            .onEach { history ->
                val latest = history.lastOrNull()
                _uiState.value = if (latest == null) {
                    ResultUiState()
                } else {
                    ResultUiState(
                        correctAnswers = latest.correctAnswers,
                        totalQuestions = latest.totalQuestions,
                        iqValue = latest.iqValue,
                        latestResultId = latest.id
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}
