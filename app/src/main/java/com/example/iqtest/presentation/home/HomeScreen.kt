package com.example.iqtest.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onStartTest: () -> Unit,
    onOpenLeaderboard: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Welcome ${uiState.userDisplayName}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Start a test, review leaderboard, or open settings.",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = onStartTest,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start IQ Test")
        }
        Button(
            onClick = onOpenLeaderboard,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Leaderboard")
        }
        Button(
            onClick = onOpenSettings,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Settings")
        }
    }
}
