package com.example.iqtest.presentation.leaderboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.iqtest.presentation.common.PlaceholderScreenScaffold

@Composable
fun LeaderboardScreen(
    uiState: LeaderboardUiState,
    onBack: () -> Unit
) {
    PlaceholderScreenScaffold(
        title = "Leaderboard",
        description = "Top IQ users from Firestore (placeholder until backend wiring).",
        primaryActionText = "Back",
        onPrimaryAction = onBack
    ) {
        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            uiState.errorMessage != null -> Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("Error: ${uiState.errorMessage}")
            }
            else -> LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                itemsIndexed(uiState.users) { index, user ->
                    Text(
                        text = "${index + 1}. ${user.displayName} - IQ ${user.topIq}",
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
