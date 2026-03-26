package com.example.iqtest.presentation.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.iqtest.presentation.common.PlaceholderScreenScaffold

@Composable
fun SignupScreen(
    uiState: SignupUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignupClick: (() -> Unit) -> Unit,
    onGoToLogin: () -> Unit,
    onSignupSuccess: () -> Unit
) {
    if (uiState.isSignedUp) {
        onSignupSuccess()
    }

    PlaceholderScreenScaffold(
        title = "Sign up",
        description = "Create an account to store history and submit leaderboard scores."
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { onSignupClick(onSignupSuccess) },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (uiState.isLoading) "Creating account..." else "Create account")
            }
            Button(
                onClick = onGoToLogin,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Already have an account? Login")
            }
            if (!uiState.errorMessage.isNullOrBlank()) {
                Text(text = uiState.errorMessage)
            }
            Text(
                text = "TODO: Replace this placeholder auth UI with your final design.",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
