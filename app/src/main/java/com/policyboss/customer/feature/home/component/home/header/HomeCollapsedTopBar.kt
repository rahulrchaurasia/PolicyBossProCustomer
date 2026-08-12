package com.policyboss.customer.feature.home.component.home.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.policyboss.customer.ui.theme.PolicyBossCustomerTheme

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeCollapsedTopBar(
    userName: String,
    initials: String,
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    // We use a Box with the same blue color as the top of your HeroSection gradient
    // so the transition is perfectly seamless.
    Box(
        modifier = modifier
            .background(Color(0xFF2E90FA)) 
            .padding(top = statusBarHeight)
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Title
            Text(
                text = "Welcome, $userName",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f).padding(end = 16.dp)
            )

            // Avatar Box (Identical to HeaderSection)
            Box(
                modifier = Modifier
                    .size(40.dp) // Slightly smaller for the collapsed toolbar
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f))
                    .clickable { onProfileClick() },
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
}

// =================================================================
// PREVIEW
// =================================================================

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HomeCollapsedTopBarPreview() {
    PolicyBossCustomerTheme() {
        // We provide a fixed height here in the preview because, in the real app,
        // the CollapsingScaffold calculates and provides the height modifier dynamically.
        val previewHeight = 64.dp + WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

        HomeCollapsedTopBar(
            userName = "Saurabh Yadav", // Using a sample name
            initials = "SY",
            modifier = Modifier
                .fillMaxWidth()
                .height(previewHeight),
            onProfileClick = {}
        )
    }
}