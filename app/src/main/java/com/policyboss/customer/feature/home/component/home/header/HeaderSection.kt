package com.policyboss.customer.feature.home.component.home.header

// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.dummyData.AppDummyData
import com.policyboss.customer.feature.home.model.homeState.HomeUiState
import com.policyboss.customer.feature.home.ui.main.homeScreen.HomeScreen


@Composable
fun HeaderSection(
    userName: String,
    initials: String,
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit // 1. Accept the callback
) {

    // 1. Declare state variables for BOTH texts so they scale independently
    var titleScaleMultiplier by remember { mutableFloatStateOf(1f) }
    var subtitleScaleMultiplier by remember { mutableFloatStateOf(1f) }

    // 2. Define the minimum scale limits (Floor limits)
    val minTitleScale = 0.75f     // Allow title to shrink to 75%
    val minSubtitleScale = 0.80f  // Allow subtitle to shrink to 80%

    Row(
        // 2. Apply the parent modifier here
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        ) {

            // --- TITLE TEXT ---
            Text(
                text = "Welcome back! $userName",

                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize * titleScaleMultiplier
                ),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.hasVisualOverflow && titleScaleMultiplier > minTitleScale) {
                        // Shrink by 5% and ensure it doesn't drop below 0.75f
                        titleScaleMultiplier = (titleScaleMultiplier * 0.95f).coerceAtLeast(minTitleScale)
                    }
                }
            )

            // --- SUBTITLE TEXT ---
            Text(
                text = "Start insuring smarter with Policy Boss", // (Or your long test string)
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize * subtitleScaleMultiplier
                ),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.hasVisualOverflow && subtitleScaleMultiplier > minSubtitleScale) {
                        // Shrink by 5% and ensure it doesn't drop below 0.80f
                        subtitleScaleMultiplier = (subtitleScaleMultiplier * 0.95f).coerceAtLeast(minSubtitleScale)
                    }
                }
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
                .clickable { onProfileClick() }, // 3. Trigger the callback on click
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



object HomePreviewData {

    val uiState = HomeUiState(

        userName = "Rahul",

        userInitials = "RC",

        promoBanners = AppDummyData.promoBanners,

        earningBanners = AppDummyData.earningBanners,

        curatedPolicies = AppDummyData.curatedPolicies
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {

    MaterialTheme {

        HomeScreen(
            uiState = HomePreviewData.uiState,
            onAction = {},
            modifier = Modifier,
            contentPadding = PaddingValues(),
        )
    }
}


