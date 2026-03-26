package com.example.iqtest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iqtest.core.di.AppContainer
import com.example.iqtest.presentation.auth.login.LoginScreen
import com.example.iqtest.presentation.auth.login.LoginViewModel
import com.example.iqtest.presentation.auth.signup.SignupScreen
import com.example.iqtest.presentation.auth.signup.SignupViewModel
import com.example.iqtest.presentation.home.HomeScreen
import com.example.iqtest.presentation.home.HomeViewModel
import com.example.iqtest.presentation.leaderboard.LeaderboardScreen
import com.example.iqtest.presentation.leaderboard.LeaderboardViewModel
import com.example.iqtest.presentation.onboarding.OnboardingScreen
import com.example.iqtest.presentation.onboarding.OnboardingViewModel
import com.example.iqtest.presentation.result.ResultScreen
import com.example.iqtest.presentation.result.ResultViewModel
import com.example.iqtest.presentation.settings.SettingsScreen
import com.example.iqtest.presentation.settings.SettingsViewModel
import com.example.iqtest.presentation.splash.SplashScreen
import com.example.iqtest.presentation.splash.SplashViewModel
import com.example.iqtest.presentation.test.TestScreen
import com.example.iqtest.presentation.test.TestViewModel

@Composable
fun IQTestNavHost(
    navController: NavHostController,
    appContainer: AppContainer
) {
    val factory = IQTestViewModelFactory(appContainer)

    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash.route
    ) {
        composable(AppRoute.Splash.route) {
            val viewModel: SplashViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SplashScreen(
                uiState = uiState,
                onNavigateToOnboarding = {
                    navController.navigate(AppRoute.Onboarding.route) {
                        popUpTo(AppRoute.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(AppRoute.Home.route) {
                        popUpTo(AppRoute.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.Onboarding.route) {
            val viewModel: OnboardingViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            OnboardingScreen(
                uiState = uiState,
                onComplete = {
                    viewModel.completeOnboarding()
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.Login.route) {
            val viewModel: LoginViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                uiState = uiState,
                onEmailChange = viewModel::onEmailChanged,
                onPasswordChange = viewModel::onPasswordChanged,
                onLoginClick = viewModel::login,
                onGoToSignup = { navController.navigate(AppRoute.Signup.route) },
                onLoginSuccess = {
                    navController.navigate(AppRoute.Home.route) {
                        popUpTo(AppRoute.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.Signup.route) {
            val viewModel: SignupViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SignupScreen(
                uiState = uiState,
                onEmailChange = viewModel::onEmailChanged,
                onPasswordChange = viewModel::onPasswordChanged,
                onSignupClick = viewModel::signup,
                onGoToLogin = { navController.popBackStack() },
                onSignupSuccess = {
                    navController.navigate(AppRoute.Home.route) {
                        popUpTo(AppRoute.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.Home.route) {
            val viewModel: HomeViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            HomeScreen(
                uiState = uiState,
                onStartTest = { navController.navigate(AppRoute.Test.route) },
                onOpenLeaderboard = { navController.navigate(AppRoute.Leaderboard.route) },
                onOpenSettings = { navController.navigate(AppRoute.Settings.route) }
            )
        }

        composable(AppRoute.Leaderboard.route) {
            val viewModel: LeaderboardViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LeaderboardScreen(
                uiState = uiState,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppRoute.Settings.route) {
            val viewModel: SettingsViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SettingsScreen(
                uiState = uiState,
                onLogout = {
                    viewModel.logout()
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.Home.route) { inclusive = true }
                    }
                },
                onResetOnboarding = viewModel::resetOnboarding,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppRoute.Test.route) {
            val viewModel: TestViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            TestScreen(
                uiState = uiState,
                onSelectOption = viewModel::selectOption,
                onPrevious = viewModel::goToPreviousQuestion,
                onNext = viewModel::goToNextQuestion,
                onFinish = {
                    viewModel.finishTest()
                    navController.navigate(AppRoute.Result.route)
                }
            )
        }

        composable(AppRoute.Result.route) {
            val viewModel: ResultViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ResultScreen(
                uiState = uiState,
                onGoHome = {
                    navController.navigate(AppRoute.Home.route) {
                        popUpTo(AppRoute.Home.route) { inclusive = true }
                    }
                },
                onRetake = {
                    navController.navigate(AppRoute.Test.route) {
                        popUpTo(AppRoute.Test.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
