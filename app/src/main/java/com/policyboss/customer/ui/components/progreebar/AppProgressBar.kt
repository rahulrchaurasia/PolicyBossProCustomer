package com.policyboss.customer.ui.components.progreebar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.AppColors

@Composable
fun AppProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = AppColors.BorderSecondary,
    progressColor: Color = AppColors.PrimaryBlue,
    height: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(50),
    animate: Boolean = true
) {
    val clampedProgress = progress.coerceIn(0f, 1f)

    val animatedProgress by animateFloatAsState(
        targetValue = clampedProgress,
        animationSpec = tween(durationMillis = if (animate) 300 else 0, easing = FastOutSlowInEasing),
        label = "ProgressBarAnimation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(trackColor)
    ) {
        if (animatedProgress > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = animatedProgress)
                    .fillMaxHeight()
                    .clip(shape)
                    .background(progressColor)
            )
        }
    }
}

// Convenient overload for step-based flows (e.g., currentStep = 2, totalSteps = 5)
@Composable
fun AppStepProgressBar(
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier,
    trackColor: Color = AppColors.BorderSecondary,
    progressColor: Color = AppColors.PrimaryBlue,
    height: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(50)
) {
    val fraction = if (totalSteps > 0) currentStep.toFloat() / totalSteps.toFloat() else 0f

    AppProgressBar(
        progress = fraction,
        modifier = modifier,
        trackColor = trackColor,
        progressColor = progressColor,
        height = height,
        shape = shape
    )
}

// ─────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true)
@Composable
private fun AppProgressBarPreview() {
    Box(modifier = Modifier.padding(20.dp)) {
        AppStepProgressBar(
            currentStep = 2,
            totalSteps = 5
        )
    }
}