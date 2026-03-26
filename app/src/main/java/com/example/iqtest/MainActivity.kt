package com.example.iqtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.iqtest.presentation.navigation.IQTestNavHost
import com.example.iqtest.ui.theme.IQTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as IQTestApplication
        val appContainer = app.appContainer

        setContent {
            IQTestTheme {
                val navController = rememberNavController()
                IQTestNavHost(
                    navController = navController,
                    appContainer = appContainer
                )
            }
        }
    }
}