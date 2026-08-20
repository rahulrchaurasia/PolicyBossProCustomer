package com.policyboss.customer.ui.components.background



import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun CurvedDarkBackground() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, 0f)

            lineTo(
                0f,
                height - 80.dp.toPx()
            )

            quadraticTo(
                width / 2f,
                height + 40.dp.toPx(),
                width,
                height - 120.dp.toPx()
            )

            lineTo(width, 0f)
            close()
        }

        drawPath(
            path = path,
            color = AppColors.ClaimDarkBg
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurvedDarkBackgroundPreview() {
    CurvedDarkBackground()
}