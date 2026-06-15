package com.policyboss.customer.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomAppLoader(
    modifier: Modifier = Modifier,
    showBackground: Boolean = true
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .then(
                if (showBackground) {
                    Modifier.background(
                        Color.Black.copy(alpha = 0.45f)
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.secondary,
            strokeWidth = 3.dp
        )
    }
}
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CustomAppLoaderPreview() {
    CustomAppLoader(
        showBackground = true
    )
}