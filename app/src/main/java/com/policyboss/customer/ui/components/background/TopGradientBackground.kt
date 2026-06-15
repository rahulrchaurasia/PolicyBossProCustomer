package com.policyboss.customer.ui.components.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopGradientBackground(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            // Make sure the height is enough to cover the Header, Banners, and Clouds.
            // 400.dp to 450.dp is usually the sweet spot!
            .height(200.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2E90FA), // Your top Blue color
                        Color(0xFFCBF4DF)  // Your bottom Mint Green color
                    )
                )
            )
    )
}