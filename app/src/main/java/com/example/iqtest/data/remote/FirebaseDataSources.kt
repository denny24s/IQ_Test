package com.example.iqtest.data.remote

import android.content.Context
import com.example.iqtest.domain.model.LeaderboardUser
import com.example.iqtest.domain.model.TestResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Firebase entry points are isolated here so the rest of data layer can stay testable.
 * TODO: Add Firebase App Check and security rules aligned setup when backend is ready.
 */
data class FirebaseDataSources(
    val auth: FirebaseAuth?,
    val firestore: FirebaseFirestore?
) {
    companion object {
        fun create(context: Context): FirebaseDataSources {
            val appContext = context.applicationContext
            return runCatching {
                val firebaseApp = FirebaseApp.initializeApp(appContext)
                    ?: FirebaseApp.getApps(appContext).firstOrNull()

                if (firebaseApp == null) {
                    FirebaseDataSources(auth = null, firestore = null)
                } else {
                    FirebaseDataSources(
                        auth = FirebaseAuth.getInstance(firebaseApp),
                        firestore = FirebaseFirestore.getInstance(firebaseApp)
                    )
                }
            }.getOrElse {
                // Defensive fallback: never crash app startup due to Firebase config issues.
                FirebaseDataSources(auth = null, firestore = null)
            }
        }
    }
}

class FirebaseAuthDataSource(
    private val auth: FirebaseAuth?
) {
    suspend fun login(email: String, password: String): Result<Unit> {
        if (auth == null) return firebaseNotConfiguredResult()
        // TODO: Wire FirebaseAuth signInWithEmailAndPassword + await extension.
        return Result.failure(IllegalStateException("TODO: Implement Firebase login."))
    }

    suspend fun signup(email: String, password: String): Result<Unit> {
        if (auth == null) return firebaseNotConfiguredResult()
        // TODO: Wire FirebaseAuth createUserWithEmailAndPassword + await extension.
        return Result.failure(IllegalStateException("TODO: Implement Firebase signup."))
    }

    suspend fun logout() {
        auth?.signOut()
    }

    fun isLoggedIn(): Boolean = auth?.currentUser != null

    fun currentUserId(): String? = auth?.currentUser?.uid

    private fun firebaseNotConfiguredResult(): Result<Unit> {
        return Result.failure(
            IllegalStateException(
                "Firebase is not configured. Add google-services.json and initialize Firebase."
            )
        )
    }
}

class FirestoreDataSource(
    private val firestore: FirebaseFirestore?
) {
    suspend fun submitResult(result: TestResult) {
        if (firestore == null) return
        // TODO: Wire Firestore write to leaderboard/result collections.
        // Placeholder keeps compile path and architecture ready.
    }

    suspend fun fetchLeaderboard(): List<LeaderboardUser> {
        if (firestore == null) return emptyList()
        // TODO: Wire Firestore leaderboard query.
        return emptyList()
    }
}
