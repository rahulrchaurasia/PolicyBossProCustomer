package com.policyboss.customer.anim


// 1. ENHANCED NavigationAnimations with Bidirectional Support
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically

// 3. Animation Configurations (XML slide animations)
object NavigationAnimations {
    private const val ANIMATION_DURATION = 300

    // Forward Navigation (Left to Right)
    val slideInRight = slideInHorizontally(
        initialOffsetX = { it }, // Start from right edge
        animationSpec = tween(ANIMATION_DURATION)
    )

    val slideOutLeft = slideOutHorizontally(
        targetOffsetX = { -it }, // Exit to left edge
        animationSpec = tween(ANIMATION_DURATION)
    )

    // Backward Navigation (Right to Left) - NEW
    val slideInLeft = slideInHorizontally(
        initialOffsetX = { -it }, // Start from left edge
        animationSpec = tween(ANIMATION_DURATION)
    )

    val slideOutRight = slideOutHorizontally(
        targetOffsetX = { it }, // Exit to right edge
        animationSpec = tween(ANIMATION_DURATION)
    )

    // Fade animations for special cases
    val fadeIn = fadeIn(animationSpec = tween(ANIMATION_DURATION))
    val fadeOut = fadeOut(animationSpec = tween(ANIMATION_DURATION))

    // 🚀 NEW: Vertical Animations (Bottom-Up Modals)

    // 1. Enter from the bottom of the screen (Moving into Profile)
    val slideInBottom = slideInVertically(
        initialOffsetY = { it }, // Start at bottom edge
        animationSpec = tween(ANIMATION_DURATION)
    )

    // 2. Exit through the top of the screen (Navigating DEEPER from Profile)
    val slideOutTop = slideOutVertically(
        targetOffsetY = { -it }, // Exit to top edge
        animationSpec = tween(ANIMATION_DURATION)
    )

    // 3. Enter from the top of the screen (Returning TO Profile from deeper screen)
    val slideInTop = slideInVertically(
        initialOffsetY = { -it }, // Start at top edge
        animationSpec = tween(ANIMATION_DURATION)
    )

    // 4. Exit through the bottom of the screen (Closing Profile completely)
    val slideOutBottom = slideOutVertically(
        targetOffsetY = { it }, // Exit to bottom edge
        animationSpec = tween(ANIMATION_DURATION)
    )
}