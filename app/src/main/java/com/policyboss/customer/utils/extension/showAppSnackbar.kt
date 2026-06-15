package com.policyboss.customer.utils.extension

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

/**
 * Reusable extension to show a snackbar with standard configuration.
 */
suspend fun SnackbarHostState.showAppSnackbar(
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration = SnackbarDuration.Short,
    onAction: (() -> Unit)? = null
) {
    val result = showSnackbar(
        message = message,
        actionLabel = actionLabel,
        duration = duration
    )
    
    if (result == SnackbarResult.ActionPerformed) {
        onAction?.invoke()
    }
}



@Composable

fun SnackBarDemoScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showAppSnackbar(
                            message = "Contact Sync Started",
                            actionLabel = "Settings",
                            onAction = {
                                // Handle action click
                            }
                        )
                    }
                }
            ) {
                Text("Trigger Snackbar")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SnackBarDemoPreview() {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar("Preview Snackbar")
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // ✅ FIX
        )
    }
}