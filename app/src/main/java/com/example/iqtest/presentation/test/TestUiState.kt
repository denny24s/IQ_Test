package com.example.iqtest.presentation.test

import com.example.iqtest.domain.model.Question
import com.example.iqtest.domain.model.TestResult
import com.example.iqtest.domain.model.UserAnswer

data class TestUiState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val answers: List<UserAnswer> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val totalDurationMillis: Long = 30 * 60 * 1000L,
    val remainingMillis: Long = 30 * 60 * 1000L,
    val errorMessage: String? = null,
    val lastResult: TestResult? = null
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)

    val remainingMinutes: Long
        get() = remainingMillis / 60_000

    val remainingSecondsPart: Long
        get() = (remainingMillis / 1_000) % 60
}
