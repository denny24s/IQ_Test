package com.example.iqtest.domain.repository

import com.example.iqtest.domain.model.LeaderboardUser
import com.example.iqtest.domain.model.Question
import com.example.iqtest.domain.model.TestResult
import com.example.iqtest.domain.model.UserAnswer
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun loadQuestions(): List<Question>
    suspend fun saveResult(result: TestResult)
    suspend fun submitResultToLeaderboard(result: TestResult)
    fun observeResultHistory(): Flow<List<TestResult>>
    suspend fun getLeaderboard(): List<LeaderboardUser>
    suspend fun calculateResult(
        userId: String,
        answers: List<UserAnswer>,
        questions: List<Question>
    ): TestResult
}
