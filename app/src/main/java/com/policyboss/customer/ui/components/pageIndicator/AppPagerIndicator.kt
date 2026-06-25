package com.policyboss.customer.ui.components.pageIndicator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppPagerIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF2E90FA),
    inactiveColor: Color = activeColor.copy(alpha = 0.3f),
    activeWidth: Dp = 24.dp,    // Stretches out if higher than inactiveWidth
    inactiveWidth: Dp = 8.dp,   // Standard base dot/line size
    indicatorHeight: Dp = 8.dp, // 8dp gives perfect round dots, 4dp gives thin lines
    isAnimated: Boolean = true  // Toggle animation for smooth worm effect vs instant state change
) {
    // Edge case guard: don't render anything if there's only 1 page
    if (totalPages <= 1) return

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            val isSelected = index == currentPage

            // Calculate width based on animation flag


            val width by if (isAnimated) {
                animateDpAsState(
                    targetValue = if (isSelected) activeWidth else inactiveWidth,
                    label = "app_pager_indicator_width"
                )
            } else {
                androidx.compose.runtime.rememberUpdatedState(
                    newValue = if (isSelected) activeWidth else inactiveWidth
                )
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .width(width)
                    .height(indicatorHeight)
                    .clip(CircleShape)
                    .background(
                        color = if (isSelected) activeColor else inactiveColor
                    )
            )
        }
    }
}


///Use Case1




@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AppPagerIndicatorPreviews() {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 1. Video Slider Style (Uniform Round Dots)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "1. Video Slider Style (Uniform Dots)",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AppPagerIndicator(
                totalPages = 4,
                currentPage = 2,
                activeColor = Color(0xFF5F6E85),
                inactiveColor = Color(0xFFD3D8E1),
                activeWidth = 8.dp,     // Matches height for a perfect circle
                inactiveWidth = 8.dp,
                indicatorHeight = 8.dp,
                isAnimated = false
            )
        }

        // 2. Onboarding Style (Expanding Worm / Pill)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "2. Onboarding Style (Expanding Worm)",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AppPagerIndicator(
                totalPages = 4,
                currentPage = 1,
                activeColor = Color(0xFF1887FF),
                inactiveColor = Color(0xFF1887FF).copy(alpha = 0.3f),
                activeWidth = 24.dp,    // Stretches to a pill
                inactiveWidth = 8.dp,
                indicatorHeight = 8.dp, // 8dp height keeps the edges perfectly round
                isAnimated = true
            )
        }

        // 3. Flat Line Style
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "3. Flat Line Style",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AppPagerIndicator(
                totalPages = 4,
                currentPage = 0,
                activeColor = Color(0xFF2E90FA),
                inactiveColor = Color(0xFF2E90FA).copy(alpha = 0.3f),
                activeWidth = 24.dp,
                inactiveWidth = 8.dp,
                indicatorHeight = 4.dp, // 4dp height flattens it into a line
                isAnimated = true
            )
        }
    }
}
