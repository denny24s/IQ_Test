package com.example.iqtest.data.remote

import android.content.Context
import com.example.iqtest.domain.model.LeaderboardUser
import com.example.iqtest.domain.model.TestResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Firebase access is intentionally lazy and guarded so app startup never depends on Firebase setup.
 */
class FirebaseAuthDataSource(
    context: Context
) {
    private val appContext = context.applicationContext

    suspend fun login(email: String, password: String): Result<Unit> {
        val auth = getAuthOrNull() ?: return firebaseNotConfiguredResult()
        // TODO: Wire FirebaseAuth signInWithEmailAndPassword + await extension.
        return Result.failure(IllegalStateException("TODO: Implement Firebase login."))
    }

    suspend fun signup(email: String, password: String): Result<Unit> {
        val auth = getAuthOrNull() ?: return firebaseNotConfiguredResult()
        // TODO: Wire FirebaseAuth createUserWithEmailAndPassword + await extension.
        return Result.failure(IllegalStateException("TODO: Implement Firebase signup."))
    }

    suspend fun logout() {
        getAuthOrNull()?.signOut()
    }

    fun isLoggedIn(): Boolean = getAuthOrNull()?.currentUser != null

    fun currentUserId(): String? = getAuthOrNull()?.currentUser?.uid

    private fun getAuthOrNull(): FirebaseAuth? {
        return runCatching {
            val app = FirebaseApp.getApps(appContext).firstOrNull()
                ?: FirebaseApp.initializeApp(appContext)
                ?: return null
            FirebaseAuth.getInstance(app)
        }.getOrNull()
    }

    private fun firebaseNotConfiguredResult(): Result<Unit> {
        return Result.failure(
            IllegalStateException(
                "Firebase is not configured. Add google-services.json and rebuild."
            )
        )
    }
}

class FirestoreDataSource(
    context: Context
) {
    private val appContext = context.applicationContext

    suspend fun submitResult(result: TestResult) {
        val firestore = getFirestoreOrNull() ?: return
        // TODO: Wire Firestore write to leaderboard/result collections.
        // Placeholder keeps compile path and architecture ready.
    }

    suspend fun fetchLeaderboard(): List<LeaderboardUser> {
        val firestore = getFirestoreOrNull() ?: return emptyList()
        // TODO: Wire Firestore leaderboard query.
        return emptyList()
    }

    private fun getFirestoreOrNull(): FirebaseFirestore? {
        return runCatching {
            val app = FirebaseApp.getApps(appContext).firstOrNull()
                ?: FirebaseApp.initializeApp(appContext)
                ?: return null
            FirebaseFirestore.getInstance(app)
        }.getOrNull()
    }
}
