package com.policyboss.customer.ui.components.snackBar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppSnackbar = staticCompositionLocalOf<SnackbarHostState> {
    error("No SnackbarHostState provided. Make sure it is wrapped in CompositionLocalProvider.")
}