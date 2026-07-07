package com.policyboss.customer.core.extension


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.gradients.AppGradients

@Composable
fun Modifier.floatingAnimation(duration: Int, delay: Int): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "floating")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(duration, delay, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset"
    )
    return this.offset(y = offsetY.dp)
}



fun Modifier.gradientBorder(
    strokeWidth: Dp = 1.dp,
    brush: Brush = AppGradients.BorderBrush,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp)
): Modifier = this.drawBehind {
    val cornerRadiusPx = shape.topStart.toPx(size, this)
    val path = Path().apply {
        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                rect = androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadiusPx)
            )
        )
    }
    drawPath(
        path = path,
        brush = brush,
        style = Stroke(width = strokeWidth.toPx())
    )
}