package com.example.iqtest.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Firebase entry points are isolated here so the rest of data layer can stay testable.
 * TODO: Add Firebase App Check and security rules aligned setup when backend is ready.
 */
data class FirebaseDataSources(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
)

class FirebaseAuthDataSource(
    private val auth: FirebaseAuth
) {
    suspend fun login(email: String, password: String): Result<Unit> {
        // TODO: Wire FirebaseAuth signInWithEmailAndPassword + await extension.
        return Result.failure(IllegalStateException("TODO: Implement Firebase login."))
    }

    suspend fun signup(email: String, password: String): Result<Unit> {
        // TODO: Wire FirebaseAuth createUserWithEmailAndPassword + await extension.
        return Result.failure(IllegalStateException("TODO: Implement Firebase signup."))
    }

    suspend fun logout() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean = auth.currentUser != null

    fun currentUserId(): String? = auth.currentUser?.uid
}

class FirestoreDataSource(
    private val firestore: FirebaseFirestore
) {
    suspend fun submitResult(result: com.example.iqtest.domain.model.TestResult) {
        // TODO: Wire Firestore write to leaderboard/result collections.
        // Placeholder keeps compile path and architecture ready.
    }

    suspend fun fetchLeaderboard(): List<com.example.iqtest.domain.model.LeaderboardUser> {
        // TODO: Wire Firestore leaderboard query.
        return emptyList()
    }
}
