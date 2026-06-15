package com.policyboss.customer.core.extension

import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

//fun Modifier.floatingAnimation(delayStart: Int = 0): Modifier = composed {
//
//    val infiniteTransition = rememberInfiniteTransition(label = "floating")
//
//    val offset by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 12f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = 2000,
//                delayMillis = delayStart,
//                easing = FastOutSlowInEasing
//            ),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = "offset"
//    )
//
//    this.offset(y = offset.dp)
//}


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