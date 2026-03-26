package com.example.iqtest.presentation.result

data class ResultUiState(
    val correctAnswers: Int = 0,
    val totalQuestions: Int = 97,
    val iqValue: Int = 0,
    val latestResultId: String = ""
)
