package com.example.iqtest.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iqtest.core.di.AppContainer
import com.example.iqtest.presentation.auth.login.LoginViewModel
import com.example.iqtest.presentation.auth.signup.SignupViewModel
import com.example.iqtest.presentation.home.HomeViewModel
import com.example.iqtest.presentation.leaderboard.LeaderboardViewModel
import com.example.iqtest.presentation.onboarding.OnboardingViewModel
import com.example.iqtest.presentation.result.ResultViewModel
import com.example.iqtest.presentation.settings.SettingsViewModel
import com.example.iqtest.presentation.splash.SplashViewModel
import com.example.iqtest.presentation.test.TestViewModel

class IQTestViewModelFactory(
    private val appContainer: AppContainer
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(
                    userPreferencesRepository = appContainer.userPreferencesRepository,
                    authRepository = appContainer.authRepository
                ) as T
            }

            modelClass.isAssignableFrom(OnboardingViewModel::class.java) -> {
                OnboardingViewModel(appContainer.userPreferencesRepository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(appContainer.authRepository) as T
            }

            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(appContainer.authRepository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel() as T
            }

            modelClass.isAssignableFrom(LeaderboardViewModel::class.java) -> {
                LeaderboardViewModel(appContainer.testRepository) as T
            }

            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> {
                SettingsViewModel(
                    authRepository = appContainer.authRepository,
                    userPreferencesRepository = appContainer.userPreferencesRepository
                ) as T
            }

            modelClass.isAssignableFrom(TestViewModel::class.java) -> {
                TestViewModel(
                    authRepository = appContainer.authRepository,
                    testRepository = appContainer.testRepository
                ) as T
            }

            modelClass.isAssignableFrom(ResultViewModel::class.java) -> {
                ResultViewModel(appContainer.testRepository) as T
            }

            else -> throw IllegalArgumentException(
                "Unknown ViewModel class: ${modelClass.name}"
            )
        }
    }
}
