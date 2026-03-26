package com.example.iqtest.data.mapper

import com.example.iqtest.domain.model.TestResult

fun TestResult.toFirestoreMap(): Map<String, Any> {
    return mapOf(
        "correctAnswers" to correctAnswers,
        "totalQuestions" to totalQuestions,
        "iqValue" to iqValue,
        "finishedAtMillis" to finishedAtMillis
    )
}
