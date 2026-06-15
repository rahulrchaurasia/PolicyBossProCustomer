package com.policyboss.customer.feature.login.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.R




@Composable
fun AuthHeaderPattern(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.42f)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF071120),
                        Color(0xFF0F172A)
                    )
                )
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.bg_pattern),

            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.FillWidth,

            alignment = Alignment.TopStart,

            alpha = 0.45f
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AuthHeaderPatternPreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF071120))
    ) {

        AuthHeaderPattern()
    }
}