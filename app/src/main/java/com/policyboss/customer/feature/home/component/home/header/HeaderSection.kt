package com.policyboss.customer.feature.home.component.home.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape

// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.policyboss.customer.feature.home.dummy.HomeDummyData
import com.policyboss.customer.feature.home.model.home.HomeUiState
import com.policyboss.customer.feature.home.ui.HomeScreen

@Composable
fun HeaderSection(
    userName: String,
    initials: String,
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit // 1. Accept the callback
) {
    Row(
        // 2. Apply the parent modifier here
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome back! $userName",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = "Start insuring smarter with Policy Boss",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
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

        promoBanners = HomeDummyData.promoBanners,

        earningBanners = HomeDummyData.earningBanners ,

        curatedPolicies = HomeDummyData.curatedPolicies
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
            onAction = {}
        )
    }
}


