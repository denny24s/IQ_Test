package com.example.iqtest.presentation.auth.login

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
fun LoginScreen(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: (() -> Unit) -> Unit,
    onGoToSignup: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    PlaceholderScreenScaffold(
        title = "Login",
        description = "Firebase Authentication placeholder screen."
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
                onClick = { onLoginClick(onLoginSuccess) },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (uiState.isLoading) "Loading..." else "Login")
            }
            Button(onClick = onGoToSignup, modifier = Modifier.fillMaxWidth()) {
                Text("Go to Sign up")
            }
            if (uiState.error != null) {
                Text(uiState.error)
            }
            Text(
                text = "TODO: Replace with final auth screen design.",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
