package com.example.iqtest.core.di

import android.content.Context
import com.example.iqtest.data.local.QuestionsLocalDataSource
import com.example.iqtest.data.local.UserPreferencesDataSource
import com.example.iqtest.data.remote.FirebaseAuthDataSource
import com.example.iqtest.data.remote.FirestoreDataSource
import com.example.iqtest.data.repository.AuthRepositoryImpl
import com.example.iqtest.data.repository.TestRepositoryImpl
import com.example.iqtest.data.repository.UserPreferencesRepositoryImpl
import com.example.iqtest.domain.repository.AuthRepository
import com.example.iqtest.domain.repository.TestRepository
import com.example.iqtest.domain.repository.UserPreferencesRepository

interface AppContainer {
    val authRepository: AuthRepository
    val userPreferencesRepository: UserPreferencesRepository
    val testRepository: TestRepository
}

class DefaultAppContainer(
    context: Context
) : AppContainer {

    private val appContext = context.applicationContext

    private val authDataSource = FirebaseAuthDataSource(appContext)
    private val firestoreDataSource = FirestoreDataSource(appContext)
    private val userPreferencesDataSource = UserPreferencesDataSource(appContext)
    private val questionsLocalDataSource = QuestionsLocalDataSource(appContext)

    override val authRepository: AuthRepository =
        AuthRepositoryImpl(authDataSource)

    override val userPreferencesRepository: UserPreferencesRepository =
        UserPreferencesRepositoryImpl(userPreferencesDataSource)

    override val testRepository: TestRepository =
        TestRepositoryImpl(questionsLocalDataSource, firestoreDataSource)
}
