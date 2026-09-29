package com.policyboss.customer.ui.components.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppAnimatedErrorText(
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = errorMessage != null,
        modifier = modifier
    ) {
        Text(
            text = errorMessage.orEmpty(),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            // Standardized padding for inline errors
            modifier = Modifier.padding(top = 8.dp, start = 4.dp) 
        )
    }
}