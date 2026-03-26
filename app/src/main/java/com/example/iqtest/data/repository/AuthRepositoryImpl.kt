package com.example.iqtest.data.repository

import com.example.iqtest.data.remote.FirebaseAuthDataSource
import com.example.iqtest.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: FirebaseAuthDataSource
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return authDataSource.login(email, password)
    }

    override suspend fun signup(email: String, password: String): Result<Unit> {
        return authDataSource.signup(email, password)
    }

    override suspend fun logout() {
        authDataSource.logout()
    }

    override fun isLoggedIn(): Boolean {
        return authDataSource.isLoggedIn()
    }

    override fun currentUserId(): String? {
        return authDataSource.currentUserId()
    }
}
