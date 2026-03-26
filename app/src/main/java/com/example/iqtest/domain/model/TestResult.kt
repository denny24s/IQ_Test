package com.example.iqtest.domain.model

data class TestResult(
    val id: String,
    val userId: String,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val iqValue: Int,
    val finishedAtMillis: Long
)
