package com.policyboss.customer.utils.extension

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.shimmerLoadingAnimation(
    isLoading: Boolean,
    durationMillis: Int = 1000,
    shimmerColor: Color = Color.White.copy(alpha = 0.6f)
): Modifier = composed {
    if (!isLoading) return@composed this

    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerTranslateAnimation"
    )

    val baseColor = Color(0xFFE0E0E0)
    val highlightColor = Color(0xFFF5F5F5)

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                baseColor,
                shimmerColor,
                highlightColor,
                baseColor
            ),
            start = Offset(x = translateAnimation.value - 200f, y = translateAnimation.value - 200f),
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    )
}