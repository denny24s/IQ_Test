package com.example.iqtest.presentation.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestScreen(
    uiState: TestUiState,
    onSelectOption: (questionId: Int, optionIndex: Int) -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "IQ Test")
        Text(
            text = "Whole test timer: ${uiState.remainingMinutes}:${
                uiState.remainingSecondsPart.toString().padStart(2, '0')
            }"
        )
        Text(
            text = "Question ${uiState.currentQuestionIndex + 1}/${
                uiState.questions.size.coerceAtLeast(1)
            }"
        )
        Text(text = "Selected answers: ${uiState.answers.size}")
        Text(
            text = uiState.currentQuestion?.text
                ?: "No questions loaded. Add 97 questions to assets/questions.json."
        )

        uiState.currentQuestion?.options?.forEachIndexed { index, option ->
            Button(onClick = { onSelectOption(uiState.currentQuestion.id, index) }) {
                Text("${'A' + index}. $option")
            }
        }

        Button(onClick = onPrevious) { Text("Previous") }
        Button(onClick = onNext) { Text("Next") }
        Button(onClick = onFinish) { Text("Finish Test") }
    }
}
