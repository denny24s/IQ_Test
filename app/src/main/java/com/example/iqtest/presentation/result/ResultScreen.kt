package com.example.iqtest.presentation.result

import androidx.compose.runtime.Composable
import com.example.iqtest.presentation.common.PlaceholderScreenScaffold

@Composable
fun ResultScreen(
    uiState: ResultUiState,
    onGoHome: () -> Unit,
    onRetake: () -> Unit
) {
    PlaceholderScreenScaffold(
        title = "Result",
        description = "Score: ${uiState.correctAnswers}/${uiState.totalQuestions} | IQ: ${uiState.iqValue}",
        primaryActionText = "Back to Home",
        onPrimaryAction = onGoHome,
        secondaryActionText = "Retake Test",
        onSecondaryAction = onRetake
    )
}
