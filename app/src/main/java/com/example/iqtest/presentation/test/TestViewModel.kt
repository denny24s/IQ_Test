package com.example.iqtest.presentation.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqtest.domain.model.TestResult
import com.example.iqtest.domain.model.UserAnswer
import com.example.iqtest.domain.repository.AuthRepository
import com.example.iqtest.domain.repository.TestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TestViewModel(
    private val authRepository: AuthRepository,
    private val testRepository: TestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TestUiState())
    val uiState: StateFlow<TestUiState> = _uiState.asStateFlow()

    init {
        loadQuestions()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching { testRepository.loadQuestions() }
                .onSuccess { questions ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            questions = questions,
                            currentQuestionIndex = 0,
                            remainingMillis = 30L * 60L * 1000L
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Could not load questions."
                        )
                    }
                }
        }
    }

    fun selectOption(questionId: Int, selectedOptionIndex: Int) {
        _uiState.update { current ->
            val updated = current.answers
                .filterNot { it.questionId == questionId } +
                UserAnswer(questionId = questionId, selectedOptionIndex = selectedOptionIndex)
            current.copy(answers = updated)
        }
    }

    fun goToPreviousQuestion() {
        _uiState.update { current ->
            current.copy(
                currentQuestionIndex = (current.currentQuestionIndex - 1).coerceAtLeast(0)
            )
        }
    }

    fun goToNextQuestion() {
        _uiState.update { current ->
            val maxIndex = (current.questions.size - 1).coerceAtLeast(0)
            current.copy(
                currentQuestionIndex = (current.currentQuestionIndex + 1).coerceAtMost(maxIndex)
            )
        }
    }

    fun finishTest(onFinished: (TestResult) -> Unit = {}) {
        viewModelScope.launch {
            val state = _uiState.value
            val userId = authRepository.currentUserId() ?: "local_user"
            runCatching {
                val result = testRepository.calculateResult(
                    userId = userId,
                    answers = state.answers,
                    questions = state.questions
                )
                testRepository.saveResult(result)
                testRepository.submitResultToLeaderboard(result)
                result
            }.onSuccess { result ->
                _uiState.update { it.copy(lastResult = result) }
                onFinished(result)
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(errorMessage = throwable.message ?: "Failed to finish test.")
                }
            }
        }
    }
}
