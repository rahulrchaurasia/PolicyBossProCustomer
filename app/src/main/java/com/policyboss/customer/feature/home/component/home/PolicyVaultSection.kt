package com.policyboss.customer.feature.home.component.home




import androidx.compose.material.icons.Icons


// ---------------------------- IMPORTS ----------------------------

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.policyboss.customer.ui.theme.AppColors
import com.policyboss.customer.ui.theme.AppColors.CardBackground
import com.policyboss.customer.ui.theme.AppColors.TextPrimary
import com.policyboss.customer.ui.theme.AppColors.WarningYellow


@Composable
fun PolicyVaultSection(
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // 1. Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically // Added vertical alignment
        ) {
            Text(
                text = "Policy Vault",
                style = MaterialTheme.typography.titleMedium // Added typography
            )

            Text(
                text = "View All",
                style = MaterialTheme.typography.labelMedium,
                color = AppColors.BluePrimary, // Make it look clickable (use your app's link color)
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onViewAllClick() }
                    .padding(8.dp) // Added padding AFTER clickable for a better touch target and ripple
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Empty State Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(AppColors.CardBackground) // Fixed missing prefix
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "No policies info", // Good practice to give meaningful descriptions or null if purely decorative
                    tint = Color.LightGray,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(12.dp)) // Slightly larger spacing to let the icon breathe

                Text(
                    text = "No policies found",
                    color = AppColors.TextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "You are not protected!",
                    color = AppColors.TextPrimary, // Fixed missing prefix
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PolicyVaultPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            PolicyVaultSection(
                modifier = Modifier,
                onViewAllClick = {}
            )
        }
    }
}