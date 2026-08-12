package com.policyboss.customer.feature.claimSupport.claimSupportScreen.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.claimSupport.claimSupportScreen.model.ClaimTab


val SegmentHeight1 = 56.dp
 val SegmentCornerRadius1 = 20.dp
 val IndicatorPadding1 = 4.dp
@Composable
 fun SlidingIndicator1(
    selectedTab: ClaimTab,
    tabWidth: Dp,
    modifier: Modifier = Modifier
) {

    val index = selectedTab.ordinal

    val animatedOffset by animateDpAsState(
        targetValue = tabWidth * index,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "IndicatorOffset"
    )

    Surface(
        modifier = modifier
            .offset(x = animatedOffset)
            .padding(IndicatorPadding)
            .width(tabWidth - IndicatorPadding * 2)
            .fillMaxHeight(),
        shape = RoundedCornerShape(SegmentCornerRadius - 4.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 3.dp,
        tonalElevation = 0.dp
    ) {}
}