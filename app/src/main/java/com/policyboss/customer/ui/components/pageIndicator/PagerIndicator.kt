package com.policyboss.customer.ui.components.pageIndicator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp






@Composable
fun PagerIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF2E90FA),
    inactiveColor: Color = activeColor.copy(alpha = 0.3f),
    activeWidth: Dp = 24.dp,
    inactiveWidth: Dp = 8.dp,
    height: Dp = 4.dp
) {

    if (totalPages <= 1) return

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(totalPages) { index ->

            val isSelected = index == currentPage

            val width by animateDpAsState(
                targetValue = if (isSelected) activeWidth else inactiveWidth,
                label = "pager_indicator"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .width(width)
                    .height(height)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) activeColor
                        else inactiveColor
                    )
            )
        }
    }
}

