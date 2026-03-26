package com.example.iqtest.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signup(email: String, password: String): Result<Unit>
    suspend fun logout()
    fun isLoggedIn(): Boolean
    fun currentUserId(): String?
}
