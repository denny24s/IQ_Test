package com.example.iqtest.data.repository

import com.example.iqtest.data.local.QuestionsLocalDataSource
import com.example.iqtest.data.remote.FirestoreDataSource
import com.example.iqtest.domain.model.LeaderboardUser
import com.example.iqtest.domain.model.Question
import com.example.iqtest.domain.model.TestResult
import com.example.iqtest.domain.model.UserAnswer
import com.example.iqtest.domain.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import kotlin.math.roundToInt

class TestRepositoryImpl(
    private val questionsLocalDataSource: QuestionsLocalDataSource,
    private val firestoreDataSource: FirestoreDataSource
) : TestRepository {

    private val localHistory = MutableStateFlow<List<TestResult>>(emptyList())

    override suspend fun loadQuestions(): List<Question> {
        return questionsLocalDataSource.loadQuestions()
    }

    override suspend fun saveResult(result: TestResult) {
        localHistory.value = localHistory.value + result
    }

    override suspend fun submitResultToLeaderboard(result: TestResult) {
        firestoreDataSource.submitResult(result)
    }

    override fun observeResultHistory(): Flow<List<TestResult>> = localHistory

    override suspend fun getLeaderboard(): List<LeaderboardUser> {
        return firestoreDataSource.fetchLeaderboard()
    }

    override suspend fun calculateResult(
        userId: String,
        answers: List<UserAnswer>,
        questions: List<Question>
    ): TestResult {
        val answersByQuestion = answers.associateBy { it.questionId }
        val correctAnswers = questions.count { question ->
            answersByQuestion[question.id]?.selectedOptionIndex == question.correctOptionIndex
        }

        val iqValue = ((correctAnswers.toFloat() / questions.size.coerceAtLeast(1)) * MAX_IQ)
            .roundToInt()
            .coerceIn(0, MAX_IQ)

        return TestResult(
            id = UUID.randomUUID().toString(),
            userId = userId,
            correctAnswers = correctAnswers,
            totalQuestions = questions.size,
            iqValue = iqValue,
            finishedAtMillis = System.currentTimeMillis()
        )
    }

    private companion object {
        const val MAX_IQ = 240
    }
}
